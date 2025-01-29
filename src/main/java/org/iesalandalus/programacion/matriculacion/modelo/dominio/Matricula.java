package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.ArrayList;

public class Matricula {
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION=6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
    private static final String ER_CURSO_ACADEMICO="\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA= "dd/MM/YYYY";
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private ArrayList<Asignatura> coleccionAsignaturas;

    //constructor con parametros
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        //this.fechaAnulacion = null;
        //setFechaAnulacion(LocalDate.of(0001, 01, 01));
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }
    //constructor copia
    public Matricula(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        //setFechaAnulacion(matricula.getFechaAnulacion()); // Copiar fecha de anulación, puede ser nula
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
    }

    //metodo para comprobar si la matricula supera el maximo de horas
    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula) {

        int horasMatricula = 0;
       for (Asignatura asignatura : asignaturasMatricula) {
           if (asignatura != null) {
               horasMatricula += asignatura.getHorasAnuales();
           }
       }

        return horasMatricula > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    private String asignaturasMatricula(){

        /*StringBuilder asignaturas = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                asignaturas.append(asignatura.getNombre()).append(", ");
            }
        }
        if (!asignaturas.isEmpty()) {
            asignaturas.setLength(asignaturas.length() - 2);
        }
        return asignaturas.toString();*/
        String s = "";
        for(int i=0;i<coleccionAsignaturas.size();i++){
            if (coleccionAsignaturas.get(i) != null) {
                s+=coleccionAsignaturas.get(i).getNombre();
                if(i<coleccionAsignaturas.size()-1){
                    s+=", ";
                }
            }
        }
        return s;

    }


    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }
        if (cursoAcademico.isBlank() || cursoAcademico.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");

        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)){
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {

        if (fechaMatriculacion == null){
            throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {

        if (fechaAnulacion == null){
            throw new NullPointerException("La fecha de anulación no puede ser nulo");
        }
        if (fechaAnulacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        if(fechaAnulacion.isBefore(getFechaMatriculacion().minus(MAXIMO_MESES_ANTERIOR_ANULACION, ChronoUnit.MONTHS))){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }
        if(fechaAnulacion.isBefore(getFechaMatriculacion())){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }


    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        Asignatura[] copiaAsignatura= new Asignatura[coleccionAsignaturas.size()];
        return coleccionAsignaturas.toArray(copiaAsignatura);
    }

    public void setColeccionAsignaturas (Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        //Comprobar que las horas totales de todas las asignaturas de la colección no sea > 1000
        if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)){
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }

        this.coleccionAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) continue;
                this.coleccionAsignaturas.add(new Asignatura(asignatura));

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    //crear metodo imprimir
    public String imprimir(){
        return "idMatricula=" + getIdMatricula() + ", " +
                "curso académico=" + getCursoAcademico() + ", " +
                "fecha matriculación=" + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " +
                "alumno=" + "{" + getAlumno().imprimir() + "}";


    }

    public String toString() {

        if (fechaAnulacion == null){
            return "idMatricula=" + getIdMatricula() + ", " +
                    "curso académico=" + getCursoAcademico() + ", " +
                    "fecha matriculación=" + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " +
                    "alumno=" + getAlumno().imprimir() + ", " +
                    "Asignaturas=" + "{ " + asignaturasMatricula() + "}";

        } else return "idMatricula=" + getIdMatricula() + ", " +
                "curso académico=" + getCursoAcademico() + ", " +
                "fecha matriculación=" + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " +
                "fecha anulación=" + getFechaAnulacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " +
                "alumno=" + getAlumno().imprimir() + ", " +
                "Asignaturas=" + "{ " + asignaturasMatricula() + "}";
    }
}
