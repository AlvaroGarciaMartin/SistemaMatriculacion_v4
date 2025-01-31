package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class Vista {
    private static Controlador controlador;



   /* public static void main(String[] args)  {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion= Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }while(opcion!=Opcion.SALIR);

        System.out.println("Hasta luego!!!!");
    }*/
    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion= Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }while(opcion!=Opcion.SALIR);
    }
    public void terminar() {
        controlador.terminar();
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo");
        }
        this.controlador = controlador;
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Hasta luego!!");
                System.exit(0);
            case INSERTAR_ALUMNO:
                insertarAlumno();
                System.out.println("Insertar alumno");
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                System.out.println("Buscar alumno");
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                System.out.println("Borrar alumno");
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                System.out.println("Mostrar alumnos");
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                System.out.println("Insertar ciclo formativo");
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                System.out.println("Buscar ciclo formativo");
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                System.out.println("Borrar ciclo formativo");
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCicloFormativos();
                System.out.println("Mostrar ciclos formativos");
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                System.out.println("Insertar asignatura");
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                System.out.println("Buscar asignatura");
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                System.out.println("Borrar asignatura");
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                System.out.println("Mostrar asignaturas");
                break;
            case INSERTAR_MATRICULA:
                System.out.println("Insertar matricula");
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                System.out.println("Buscar matricula");
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                System.out.println("Borrar matricula");
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                System.out.println("Mostrar matriculas");
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                System.out.println("Mostrar matriculas alumno");
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                System.out.println("Mostrar matriculas ciclo formativo");
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                System.out.println("Mostrar matriculas curso academico");
                mostrarMatriculasPorCursoAcademico();
                break;

        }
    }
    //insertar alumno
    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            //alumnos.insertar(alumno);
            controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un Alumno nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Alumno
    private static void buscarAlumno() {
        try {
           //Alumno alumnoBuscado = alumnos.buscar(Consola.getAlumnoPorDni());
            //Alumno encontrado = alumnos.buscar(alumnoBuscado);
            Alumno alumnoBuscado = controlador.buscar(Consola.getAlumnoPorDni());
            Alumno encontrado = controlador.buscar(alumnoBuscado);
            if (alumnoBuscado != null) {
                System.out.printf("Los datos del alumno solicitado son: %s", alumnoBuscado);
            }
            if (encontrado != null) {
                System.out.printf("Los datos del alumno solicitado son: %s", alumnoBuscado);
            } else {
                System.out.println("No existe ningun alumno con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: 3.No se puede buscar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar Alumno
    private static void borrarAlumno() {
        try {
            Alumno alumnoBorrar = Consola.getAlumnoPorDni();
            //alumnos.borrar(alumnoBorrar);
            controlador.borrar(alumnoBorrar);
            System.out.println("Alumno borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
    //mostrar Alumnos
    private static void mostrarAlumnos() {
        Alumno [] arrayAlumnos = controlador.getAlumnos();
        if (arrayAlumnos.length >0) {
            for (Alumno alumno : arrayAlumnos) {
                System.out.println(alumno);
            }
        } else if (arrayAlumnos.length > 0) {
            Arrays.sort(arrayAlumnos, new Comparator<Alumno>(){
                @Override
                public int compare(Alumno o1, Alumno o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        }
        else{
            System.out.println("No existen alumnos.");
        }


    }
    //insertar Asignatura
    private static void insertarAsignatura() {

        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo ciclo = controlador.buscar(cicloFormativo);
            if (ciclo == null) {
                System.out.println("No existe el ciclo formativo indicado.");
                return;
            }
            Asignatura asignatura = Consola.leerAsignatura(ciclo);
            //asignaturas.insertar(asignatura);
            controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Asignatura nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Asignatura
    private static void buscarAsignatura() {
        try {
            //Asignatura asignaturaBuscar = asignaturas.buscar(Consola.getAsignaturaPorCodigo());
            //Asignatura encontrada = asignaturas.buscar(asignaturaBuscar);
            Asignatura asignaturaBuscar = controlador.buscar(Consola.getAsignaturaPorCodigo());
            Asignatura encontrada = controlador.buscar(asignaturaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos de la asignatura solicitada son: %s", asignaturaBuscar);
            } else {
                System.out.println("No existe ninguna asignatura con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una asignatura nula.");
        }
    }
    //borrar Asignatura
    private static void borrarAsignatura() {
        try {
            Asignatura asignaturaBorrar = Consola.getAsignaturaPorCodigo();
            //asignaturas.borrar(asignaturaBorrar);
            controlador.borrar(asignaturaBorrar);
            System.out.println("Asignatura borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una asignatura nula.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //mostrar Asignatura
    private static void mostrarAsignaturas() {
        //Asignatura [] arrayAsignatura = asignaturas.get();
        Asignatura [] arrayAsignatura = controlador.getAsignaturas();
        if (arrayAsignatura.length >0) {
            for (Asignatura asignatura : arrayAsignatura) {
                System.out.println(asignatura);
            }
        } else if (arrayAsignatura.length>0) {
            Arrays.sort(arrayAsignatura, new Comparator<Asignatura>(){
                @Override
                public int compare(Asignatura o1, Asignatura o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        } else {
            System.out.println("No existen asignaturas.");

        }
    }
    //insertar CicloFormativo
    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclosFormativo = Consola.leerCicloFormativo();
            //ciclosFormativos.insertar(ciclosFormativo);
            controlador.insertar(ciclosFormativo);
            System.out.println("Ciclo formativo insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar Ciclo Formativo nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar CicloFormativo
    private static void buscarCicloFormativo() {
        try {
            //CicloFormativo cicloFormativoBuscar = ciclosFormativos.buscar(Consola.getCicloFormativoPorCodigo());
            //CicloFormativo encontrada = ciclosFormativos.buscar(cicloFormativoBuscar);
            CicloFormativo cicloFormativoBuscar = controlador.buscar(Consola.getCicloFormativoPorCodigo());
            CicloFormativo encontrada = controlador.buscar(cicloFormativoBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", cicloFormativoBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar CicloFormativo
    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBorrar = Consola.getCicloFormativoPorCodigo();
            //ciclosFormativos.borrar(cicloFormativoBorrar);
            controlador.borrar(cicloFormativoBorrar);
            System.out.println("Ciclo formativo borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //mostrar CicloFormativo
    private static void mostrarCicloFormativos() {
        //CicloFormativo[] arrayCicloFormativo = ciclosFormativos.get();
        CicloFormativo[] arrayCicloFormativo = controlador.getCicloFormativos();
        if (arrayCicloFormativo.length > 0) {
            for (CicloFormativo cicloFormativo : arrayCicloFormativo) {
                System.out.println(cicloFormativo);
            }
        }
        else if (arrayCicloFormativo.length > 0) {
            Arrays.sort(arrayCicloFormativo, new Comparator<CicloFormativo>(){
                @Override
                public int compare(CicloFormativo o1, CicloFormativo o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        } else {
            System.out.println("No existen ciclos formativos.");

        }
    }
    //insertar Matricula
    private static void insertarMatricula() {
        // insertarMatricula_v0
       /* try {
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");

        */
        try{
            System.out.println("Datos del alumno:");
            Alumno alumno = Consola.leerAlumno();
            System.out.println("Asignaturas de la matricula:");
            Asignatura[] matriculaAsignaturas = Consola.elegirAsignaturasMatricula(Controlador.getAsignaturas());
            System.out.println("Datos de la matricula:");
            Matricula matricula = Consola.leerMatricula(alumno, matriculaAsignaturas);
            controlador.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Matricula
    private static void buscarMatricula() {
        try {
            //Matricula matriculaBuscar = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            //Matricula encontrada =matriculas.buscar(matriculaBuscar);
            Matricula matriculaBuscar = controlador.buscar(Consola.getMatriculaPorIdentificador());
            Matricula encontrada =controlador.buscar(matriculaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", matriculaBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //Anular Matricula
    private static void anularMatricula() {
        try{
            Alumno alumno = Consola.getAlumnoPorDni();
            //Matricula matriculaAnular = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            Matricula matriculaAnular = controlador.buscar(Consola.getMatriculaPorIdentificador());
            if (matriculaAnular != null && matriculaAnular.getAlumno().equals(alumno)) {
                controlador.borrar(matriculaAnular);
                System.out.println("indique la fecha de anulación:");
                String fechaAnulacion = (Entrada.cadena());
                LocalDate fechaAnular;
                fechaAnular = LocalDate.parse(fechaAnulacion);
                matriculaAnular.setFechaAnulacion(fechaAnular);
                System.out.println("Matricula anulada correctamente.");
            } else {
                System.out.println("No se ha encontrado la matricula o no corresponde al alumno indicado.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede anular una matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //mostrar Matriculas
    private static void mostrarMatriculas() {
        try {
            //Matricula[] arrayMatriculas = matriculas.get();
            Matricula[] arrayMatriculas = controlador.getMatriculas();
            if (arrayMatriculas.length > 0) {
                for (Matricula matricula: arrayMatriculas) {
                    System.out.println(matricula);
                }
            } else if (arrayMatriculas.length > 0) {
                Arrays.sort(arrayMatriculas, new Comparator<Matricula>(){
                    @Override
                    public int compare(Matricula m1, Matricula m2) {
                        int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                        if (comp==0) {
                            comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                        }
                        return comp;
                    }

                });
            }
            else {
                System.out.println("No existen Matriculas.");

            }
        }catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matriculas.");
        }
    }
    //mostrar Matricula por Alumno
    private static void mostrarMatriculasPorAlumno()  {
        try {

            Alumno alumno = Consola.getAlumnoPorDni();
            //Matricula[] arrayMatricula = matriculas.get();
            Matricula[] arrayMatricula = controlador.getMatriculas(alumno);
            Arrays.sort(arrayMatricula, new Comparator<Matricula>(){
                @Override
                public int compare(Matricula m1, Matricula m2) {
                    int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                    if (comp==0) {
                        comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                    }
                    return comp;
                }

            });
            for (Matricula matricula : arrayMatricula) {
                if (matricula.getAlumno().equals(alumno)) {
                    System.out.println(matricula);
                }else {
                    System.out.println("No existen matriculas para el alumno indicado.");
                }
            }
        }catch (OperationNotSupportedException e){
            System.out.println("ERROR: No se pueden mostrar matriculas por alumno");
        }
    }
    //mostrar Matricula por CicloFormativo
    private static void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        cicloFormativo = controlador.buscar(cicloFormativo);
        //Consola.mostrarCiclosFormativos(ciclosFormativos.get());
        //int codigo = Entrada.entero();
        //CicloFormativo[] CicloFormativo = ciclosFormativos.get();
        Matricula[] matriculaCiclo;
        try{
            matriculaCiclo = controlador.getMatriculas(cicloFormativo);
            if (matriculaCiclo.length == 0) {
                System.out.println("No existen matriculas para el ciclo formativo indicado.");
            }
            System.out.println("Matriculas del ciclo formativo indicado"+cicloFormativo.getCodigo()+":");
            Arrays.sort(matriculaCiclo, new Comparator<Matricula>(){
                @Override
                public int compare(Matricula m1, Matricula m2) {
                    int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                    if (comp==0) {
                        comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                    }
                    return comp;
                }
            });
            for (Matricula matricula : matriculaCiclo) {
                System.out.println(matricula);
            }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }


    }
    //mostrar Matricula por curso academico
    private static void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("indique el curso academico:");
            System.out.println("El formato del curso es YY-YY");
            String cursoAcademico = Entrada.cadena();
            //Matricula[] arrayMatriculas = matriculas.get();
            Matricula[] arrayMatriculas = controlador.getMatriculas(cursoAcademico);

            for (Matricula matricula : arrayMatriculas) {
                if (matricula == null) {
                    System.out.println("No existen matriculas para el curso academico indicado.");

                } else if (matricula.getCursoAcademico().equals(LocalDate.now().getYear())) {
                    System.out.println(matricula);
                } else if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                    System.out.println(matricula);
                } else {
                    System.out.println("No existen matriculas para el curso academico indicado.");
                }

            }
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matriculas por curso academico.");
        }

    }
}
