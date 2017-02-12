/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoagenciahotelera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author cristian
 */
public class ProyectoAgenciaHotelera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //cargamos el driver
            Class.forName("com.mysql.jdbc.Driver");

            int op;
            do {
                System.out.println("         _                                    _             _               _            _                       \n"
                        + "        / \\      __ _    ___   _ __     ___  (_)   __ _    | |__     ___   | |_    ___  | |   ___   _ __    __ _ \n"
                        + "       / _ \\    / _` |  / _ \\ | '_ \\   / __| | |  / _` |   | '_ \\   / _ \\  | __|  / _ \\ | |  / _ \\ | '__|  / _` |\n"
                        + "      / ___ \\  | (_| | |  __/ | | | | | (__  | | | (_| |   | | | | | (_) | | |_  |  __/ | | |  __/ | |    | (_| |\n"
                        + "     /_/   \\_\\  \\__, |  \\___| |_| |_|  \\___| |_|  \\__,_|   |_| |_|  \\___/   \\__|  \\___| |_|  \\___| |_|     \\__,_|\n"
                        + "                |___/                                                                                            \n"
                        + "");
                //menú que accede a cada uno de los menus con us metodos.
                System.out.println("-----------------------------------\n"
                        + "||Menu Principal                    ||\n"
                        + "----------------------------------- \n"
                        + "|1. Hotel                           |\n"
                        + "|2. Habitaciones                    |\n"
                        + "|3. Huespedes                       |\n"
                        + "|4. Pagos                           |\n"
                        + "|5. Reserva                         |\n"
                        + "|0. Salir                           |\n"
                        + "-----------------------------------");

                op = new Scanner(System.in).useLocale(Locale.US).nextInt();

                switch (op) {
                    case 1:
                        //accede al menú Hotel
                        hotel();
                        break;
                    case 2:
                        //accede al menú Habitaciones
                        habitaciones();
                        break;
                    case 3:
                        //accede al menú Huespedes
                        huespedes();
                        break;
                    case 4:
                        //accede al menú Pago
                        pagos();
                        break;
//                    case 5:
                    //accede al menú Reserva
//                        reserva();
//                        break;
                    case 0:
                        System.out.println("Saliendo...\n\nHasta Pronto");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } while (op != 0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver no encontrado");
        }

    }
//funcion que hace la conexion con mysql

    private static Connection conecta() {
        Connection con = null;
        try {
            // Realizamos la conexión
            con = DriverManager.getConnection("jdbc:mysql://localhost/AgenciaHotel", "root", "root");
            // Ok: avisamos

        } catch (Exception e) {
            System.out.println("Error de conexión");
        }

        return con;
    }

    static ResultSet ejecuta(Connection con, String sql) {
        ResultSet rs = null;

        Statement sentencia;
        try {
            sentencia = con.createStatement();
            rs = sentencia.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }

        return rs;
    }

    static void ejecutaUpdate(Connection con, String sql) {
        ResultSet rs = null;

        Statement sentencia;
        try {
            sentencia = con.createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
    }

//
//    ##     ##  #######  ######## ######## ##       ########  ######  
//    ##     ## ##     ##    ##    ##       ##       ##       ##    ## 
//    ##     ## ##     ##    ##    ##       ##       ##       ##       
//    ######### ##     ##    ##    ######   ##       ######    ######  
//    ##     ## ##     ##    ##    ##       ##       ##             ## 
//    ##     ## ##     ##    ##    ##       ##       ##       ##    ## 
//    ##     ##  #######     ##    ######## ######## ########  ######  
    static void hotel() {
        int op;
        //Menu Hotel
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------\n"
                    + "||Menu Hotel                        ||\n"
                    + "----------------------------------- \n"
                    + "|1. Crear Hotel                     |\n"
                    + "|2. Modificar Hoteles               |\n"
                    + "|3. Eliminar Hotel                  |\n"
                    + "|4. Consultas                       |\n"
                    + "|0. Volver                          |\n"
                    + "-----------------------------------");

            op = new Scanner(System.in).useLocale(Locale.US).nextInt();

            switch (op) {
                case 1:
                    //accede a la funcion para crear un hotel
                    crearHoteles();
                    break;
                case 2:
                    //accede a la funcion para modificar un hotel
                    modificarHoteles();
                    break;
                case 3:
                    //accede a la funcion para borrar un hotel
                    borrarHotel();
                    break;
                case 4:
                    //accede a la funcion para mostrar todos los hoteles
                    buscarHoteles();
                    break;
                case 0:
                    System.out.println("Volviendo...\n\n");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (op != 0);
    }

    private static void crearHoteles() {
        try {
            System.out.println("/////////////////////////////////////////");
            //muestra la funcion para mostrar todos los hoteles que estan creados
            mostrarTodosHoteles();
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. codigo: ");
            String codigo = new Scanner(System.in).nextLine();
            //comprobamos que si ese codigo de Hotel que le pasamos existe, si existe volvemos a pedir hasta que no coincida
            while (buscarCodigoHotel(codigo) == true) {
                System.out.println("Ya existe este Hotel. Vuelve a introducirlo de nuevo");
                System.out.println("1. codigo: ");
                codigo = new Scanner(System.in).nextLine();
            }
            System.out.println("2. nombre: ");
            String nombre = new Scanner(System.in).nextLine();

            System.out.println("3. estrellas: ");
            int estrellas = new Scanner(System.in).nextInt();
            while (estrellas < 0 || estrellas > 5) {
                System.out.println("EStá fuera del rango permitido. Vuelve a introducirlo de nuevo");
                System.out.println("3. estrellas: ");
                estrellas = new Scanner(System.in).nextInt();
            }
            System.out.println("4. email: ");
            String email = new Scanner(System.in).nextLine();
            //comprobamos que el email que le pasamos no coincida, porque los emails no pueden coincidr, son unicos
            while (buscarEmailHotel(email) == true) {
                System.out.println("Ya existe este email. Vuelve a introducirlo de nuevo");
                System.out.println("4. email: ");
                email = new Scanner(System.in).nextLine();
            }
            System.out.println("5. direccion: ");
            String direccion = new Scanner(System.in).nextLine();
            System.out.println("6. Año de contruccion: ");
            String year = new Scanner(System.in).nextLine();
// creamos el hotel con el siguente INSERT
            String sql = "INSERT Hotel (codigo, nombre, estrellas, email, direccion, anoconstruccion) values"
                    + "('" + codigo + "','" + nombre + "','" + estrellas + "','" + email + "','" + direccion + "','" + year + "');";
            System.out.println("Creado con exito");

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    private static void modificarHoteles() {
        System.out.println("/////////////////////////////////////////");
        //muestra la funcion para mostrar todos los hoteles que estan creados
        mostrarTodosHoteles();
        System.out.println("/////////////////////////////////////////");
        System.out.println("Elija un codigo de hotel (primera fila) para modificar");
        String codigo = new Scanner(System.in).nextLine();
        // comprobamos que el codigo de hotel que le pasamos coincida con el hotel que queremos modificar, si no coincide que vuelva repetir
        while (!buscarCodigoHotel(codigo) == true) {
            System.out.println("No existe este Hotel. Vuelve a introducirlo de nuevo");
            System.out.println("1. codigo Hotel: ");
            codigo = new Scanner(System.in).nextLine();
        }
        try {
            System.out.println("2. nombre: ");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("3. estrellas: ");
            int estrellas = new Scanner(System.in).nextInt();
            while (estrellas < 0 || estrellas > 5) {
                System.out.println("EStá fuera del rango permitido. Vuelve a introducirlo de nuevo");
                System.out.println("3. estrellas: ");
                estrellas = new Scanner(System.in).nextInt();
            }
            String email = new Scanner(System.in).nextLine();
            while (buscarEmailHotel(email) == true) {
                System.out.println("Ya existe este email. Vuelve a introducirlo de nuevo");
                System.out.println("4. email: ");
                email = new Scanner(System.in).nextLine();
            }
            System.out.println("5. direccion: ");
            String direccion = new Scanner(System.in).nextLine();
            System.out.println("6. Año de construccion: ");
            String year = new Scanner(System.in).nextLine();
// hacemos que se modifique los datos del hotel pasado con los nuevos que hayamos introducido
            String sql = "UPDATE Hotel SET nombre='" + nombre + "' ,estrellas= '" + estrellas + "' ,email='" + email + "' ,direccion='" + direccion + "' ,anoconstruccion='" + year + "' WHERE codigo= '" + codigo + "';";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    private static void borrarHotel() {
        try {
            //pasamos la fucion que muestre los hoteles disponibles
            System.out.println("/////////////////////////////////////////");
            mostrarTodosHoteles();
            System.out.println("/////////////////////////////////////////");
            System.out.println("Elija un codigo de hotel (primera fila) para modificar");
            String codigo = new Scanner(System.in).nextLine();
// comprobamos que el codigo de hotel que le pasamos coincida con el hotel que queremos modificar, 
//  si no coincide que vuelva repetir
            while (!buscarCodigoHotel(codigo) == true) {
                System.out.println("No existe este Hotel. Vuelve a introducirlo de nuevo");
                System.out.println("1. introduce de nuevo un codigo Hotel: ");
                codigo = new Scanner(System.in).nextLine();
            }
            //pedimos que se le de que a S para confirmar que queremos borrar, si introducimos otra cosa, se abortará
            System.out.println("Estas seguro de querer eliminar el Hotel con codigo: " + codigo + " (S/N)");
            String res = new Scanner(System.in).nextLine();
            res.equalsIgnoreCase(res);
            if (res.equalsIgnoreCase("s")) {
                //borra un hotel que pasamos por codigo Hotel
                String sql = "DELETE FROM Hotel where codigo = '" + codigo + "';";

                System.out.println("Borrado con exito.");
                try (Connection con = conecta()) {
                    ejecutaUpdate(con, sql);
                }
            } else {
                System.out.println("Misión abortada, no se borró el Hotel");

            }

        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }
//devuelve true o false si existe un hotel con el nombre que se le haya pasado

    static boolean buscarNombreHotel(String nombre) {
        String sql = "SELECT * FROM Hotel WHERE nombre = '" + nombre + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");;
        }
        return s;
    }
//devuelve true o false si existe el email que se le haya pasado a comprobar

    static boolean buscarEmailHotel(String mail) {
        String sql = "SELECT * FROM Hotel WHERE email = '" + mail + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");;
        }
        return s;
    }
//devuelve true o false si existe el hotel que buscamos pasado como codigo

    static boolean buscarCodigoHotel(String codigo) {
        String sql = "SELECT * FROM Hotel WHERE codigo = '" + codigo + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }
//consulta que muestra todos los hoteles disponibles con todos sus campos

    static boolean mostrarTodosHoteles() {
        String sql = "SELECT * FROM Hotel;";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res = rs.getString("Hotel.codigo") + ", " + rs.getString("Hotel.nombre") + ", " + rs.getString("Hotel.estrellas")
                        + ", " + rs.getString("Hotel.email") + ", " + rs.getString("Hotel.direccion")
                        + ", " + rs.getString("Hotel.anoconstruccion");
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }
//buscar un hotel pasando un nombre para buscar y mostra todos sus campos

    static boolean buscarHoteles() {
        mostrarTodosHoteles();
        System.out.println("Introduce el nombre del Hotel a buscar: ");
        String nombre = new Scanner(System.in).nextLine();
        buscarNombreHotel(nombre);

        String sql = "SELECT * FROM Hotel WHERE nombre LIKE '" + nombre + "%';";
        boolean s = false;
        int cont = 0;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res = rs.getString("Hotel.codigo") + ", " + rs.getString("Hotel.nombre")
                        + ", " + rs.getString("Hotel.estrellas") + ", " + rs.getString("Hotel.email")
                        + ", " + rs.getString("Hotel.direccion") + ", " + rs.getString("Hotel.anoconstruccion");
                System.out.println(res);
                cont++;
            }
            if (cont == 0) {
                System.out.println("No exite registros de este Hotel");
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL");;
        }
        return s;
    }
//    
//    
//    
//
//    ##     ##    ###    ########  #### ########    ###     ######  ####  #######  ##    ## ########  ######  
//    ##     ##   ## ##   ##     ##  ##     ##      ## ##   ##    ##  ##  ##     ## ###   ## ##       ##    ## 
//    ##     ##  ##   ##  ##     ##  ##     ##     ##   ##  ##        ##  ##     ## ####  ## ##       ##       
//    ######### ##     ## ########   ##     ##    ##     ## ##        ##  ##     ## ## ## ## ######    ######  
//    ##     ## ######### ##     ##  ##     ##    ######### ##        ##  ##     ## ##  #### ##             ## 
//    ##     ## ##     ## ##     ##  ##     ##    ##     ## ##    ##  ##  ##     ## ##   ### ##       ##    ## 
//    ##     ## ##     ## ########  ####    ##    ##     ##  ######  ####  #######  ##    ## ########  ######  

    static void habitaciones() {
        int op;
        do {
            //menu de habitaciones
            System.out.println("\n");
            System.out.println("-----------------------------------\n"
                    + "||Menu Habitaciones                ||\n"
                    + "----------------------------------- \n"
                    + "|1. Crear Habitación                |\n"
                    + "|2. Modificar Habitaciones          |\n"
                    + "|3. Eliminar Habitaciones           |\n"
                    + "|4. Consultas                       |\n"
                    + "|0. Volver                          |\n"
                    + "-----------------------------------");

            op = new Scanner(System.in).useLocale(Locale.US).nextInt();

            switch (op) {
                case 1:
                    //funcion que crea habitaciones
                    crearHabitaciones();
                    break;
                case 2:
                    //funcion que modifica habitaciones
                    modificarHabitaciones();
                    break;
                case 3:
                    //funcion que borra las habitaciones
                    borrarHabitaciones();
                    break;
                case 4:
                    //funcion que muestra todas las habtiaciones disponibles
                    mostrarTodasHabitaciones();
                    break;
                case 0:
                    System.out.println("Volviendo...\n\n");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (op != 0);
    }
//funcion que crean habitaciones

    private static void crearHabitaciones() {
        try {
            //funcion que muestra todas las habitaaciones disponibles
            System.out.println("/////////////////////////////////////////");
            System.out.println("Hoteles\n");
            System.out.println(buscarHoteles());
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. Introduce el Hotel donde se desea introducir nueva habitacion: ");
            String codigo = new Scanner(System.in).nextLine();
            //comprueba si el hotel que introducimos existe, si existe sigue hacia delante, si no vuevle a pedir otro hotel que existe
            while (!buscarCodigoHotel(codigo) == true) {
                System.out.println("No existe este Hotel. Vuelve a introducirlo de nuevo");
                System.out.println("1. introduce de nuevo un codigo Hotel: ");
                codigo = new Scanner(System.in).nextLine();
            }
            //esta funcion muestras las habitacioens creada por el hotel que le pasamos
            System.out.println("/////////////////////////////////////////");
            mostrarHabitacionesPasadas(codigo);
            System.out.println("/////////////////////////////////////////");
            System.out.println("2. Numero de habitacion: ");
            String numero = new Scanner(System.in).nextLine();
            //comprueba que si la habitacion pasada existe y en el hotel que este creado
            while (compruebaExisteHabitacionHotel(codigo, numero) == true) {
                System.out.println("Ya existe esta Habitacion. Vuelve a introducirlo de nuevo");
                System.out.println("2. VUelve a introdcor otro numero de habitacion: ");
                numero = new Scanner(System.in).nextLine();
            }
            //el precio de las habitaciones las iniciamos a 0
            int valor = 0;

            System.out.println("3. Tipo de Habitación (individual, doble, triple, suites, nupcial: ");
            String tipo = new Scanner(System.in).useLocale(Locale.US).nextLine();
            // el while hace saber que si no elegimos el tipo de habitacion adecuado, volvera salir mensajes que lo intentemos
            //,y dependendiendo de cual eligamos tendra un valor u otro
            while (!tipo.equalsIgnoreCase("individual") && !tipo.equalsIgnoreCase("doble") && !tipo.equalsIgnoreCase("triple") && !tipo.equalsIgnoreCase("suites") && !tipo.equalsIgnoreCase("nupcial")) {
                System.out.println("Selecciona correctamente una habitacion (individual, doble, triple, suites, nupcial. Vuelve a introducirlo de nuevo");
                tipo = new Scanner(System.in).nextLine();
            }
            if (tipo.equalsIgnoreCase("individual")) {
                valor = 50;
            } else if (tipo.equalsIgnoreCase("doble")) {
                valor = 65;
            } else if (tipo.equalsIgnoreCase("triple")) {
                valor = 95;
            } else if (tipo.equalsIgnoreCase("suites")) {
                valor = 80;
            } else if (tipo.equalsIgnoreCase("nupcial")) {
                valor = 100;
            }
// creamos el insert de habitaciones
            String sql = "INSERT Habitaciones (codigoHotel, numHabitaciones, tipo, valorHab) values"
                    + "('" + codigo + "','" + numero + "','" + tipo + "','" + valor + "');";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    private static void modificarHabitaciones() {
        try {
            System.out.println("/////////////////////////////////////////");
            System.out.println("Hoteles\n");
            //Funcion de los hoteles que esan creados
            System.out.println(mostrarTodosHoteles());
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. Introduce el Hotel donde se desea introducir nueva habitacion: ");
            //comprobamos que el hotel pasado existe, si no existe vuelve a pedir hasta que coincida
            String codigo = new Scanner(System.in).nextLine();
            while (!buscarCodigoHotel(codigo) == true) {
                System.out.println("No existe este Hotel. Vuelve a introducirlo de nuevo");
                System.out.println("1. introduce de nuevo un codigo Hotel: ");
                codigo = new Scanner(System.in).nextLine();
            }
            System.out.println("/////////////////////////////////////////");
            //muestra las habitaciones del hotel que le hayamos pasado
            mostrarHabitacionesPasadas(codigo);
            System.out.println("/////////////////////////////////////////");
            System.out.println("2. Numero de habitacion: ");
            String numero = new Scanner(System.in).nextLine();
            //comprueba que la habitacion existe dentr odel hotel pasado
            while (!compruebaExisteHabitacionHotel(codigo, numero) == true) {
                System.out.println("No existe esta Habitacion. Vuelve a introducirlo de nuevo");
                System.out.println("2. VUelve a introdcor otro numero de habitacion: ");
                numero = new Scanner(System.in).nextLine();
            }
            int valor = 0;

            System.out.println("3. Tipo de Habitación (individual, doble, triple, suites, nupcial: ");
            String tipo = new Scanner(System.in).useLocale(Locale.US).nextLine();
            // el while hace saber que si no elegimos el tipo de habitacion adecuado, volvera salir mensajes que lo intentemos
            //,y dependendiendo de cual eligamos tendra un valor u otro
            while (!tipo.equalsIgnoreCase("individual") && !tipo.equalsIgnoreCase("doble") && !tipo.equalsIgnoreCase("triple") && !tipo.equalsIgnoreCase("suites") && !tipo.equalsIgnoreCase("nupcial")) {
                System.out.println("Selecciona correctamente una habitacion (individual, doble, triple, suites, nupcial. Vuelve a introducirlo de nuevo");
                tipo = new Scanner(System.in).nextLine();
            }
            if (tipo.equalsIgnoreCase("individual")) {
                valor = 50;
            } else if (tipo.equalsIgnoreCase("doble")) {
                valor = 65;
            } else if (tipo.equalsIgnoreCase("triple")) {
                valor = 95;
            } else if (tipo.equalsIgnoreCase("suites")) {
                valor = 80;
            } else if (tipo.equalsIgnoreCase("nupcial")) {
                valor = 100;
            }
//creamos el UPDATE con las modificacioens que hayamos hecho de la habitacion en el hotel correspondiente
            String sql = "UPDATE Habitaciones SET tipo= '" + tipo + "',valorHab= '" + valor + "' WHERE codigoHotel= '" + codigo + "' and numHabitaciones='" + numero + "';";
            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

// borrar registro de habitaciones
    private static void borrarHabitaciones() {
        try {
            System.out.println("/////////////////////////////////////////");
            System.out.println("Hoteles\n");
            //muestra los hoteles disponibles
            System.out.println(mostrarTodosHoteles());
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. Introduce el Hotel donde se desea buscar la habitacion a borrar habitacion: ");
            String codigo = new Scanner(System.in).nextLine();
            //comprobamos que el hotel pasado existe, si no existe vuelve a pedir hasta que coincida
            while (!buscarCodigoHotel(codigo) == true) {
                System.out.println("No existe este Hotel. Vuelve a introducirlo de nuevo");
                System.out.println("1. introduce de nuevo un codigo Hotel: ");
                codigo = new Scanner(System.in).nextLine();
            }
            System.out.println("/////////////////////////////////////////");
            mostrarHabitacionesPasadas(codigo);
            //muestra las habitaciones del hotel que hayamos pasado
            System.out.println("/////////////////////////////////////////");
            System.out.println("2. Numero de habitacion: ");
            String numero = new Scanner(System.in).nextLine();
            //comprueba que si la habitacion elegida coincide en el hotel que estamos buscando
            while (!compruebaExisteHabitacionHotel(codigo, numero) == true) {
                System.out.println("No existe esta Habitacion. Vuelve a introducirlo de nuevo");
                System.out.println("2. VUelve a introdcor otro numero de habitacion: ");
                numero = new Scanner(System.in).nextLine();
            }
            // y confirmacion que si queremos borrar la habtiacion del hotel x
            System.out.println("Estas seguro de querer eliminar la habitacion Nº: " + numero + " en el Hotel " + codigo + " (S/N)");
            String res = new Scanner(System.in).nextLine();
            res.equalsIgnoreCase(res);
            if (res.equalsIgnoreCase("s")) {
                //borra el hotel con el numero de habtiacion y hotel que le hayamos indicado
                String sql = "DELETE FROM Habitaciones where numHabitaciones = '" + numero + "' and codigoHotel= '" + codigo + "' ;";

                System.out.println("Borrado con exito.");
                try (Connection con = conecta()) {
                    ejecutaUpdate(con, sql);
                }
            } else {
                System.out.println("Misión abortada, no se borró el Hotel");

            }

        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    //funcion que muestra las habitaciones ordenadas por codigo de Hotel
    static boolean mostrarTodasHabitaciones() {
        String sql = "SELECT * FROM Habitaciones order by codigoHotel;";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res
                        = rs.getString("Habitaciones.codigoHotel") + ", "
                        + rs.getString("Habitaciones.numHabitaciones")
                        + ", " + rs.getString("Habitaciones.tipo") + ", "
                        + rs.getString("Habitaciones.valorHab");
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

    //muestra el registro completo de las habitaciones del hotel que le pasemos como parametro
    static boolean mostrarHabitacionesPasadas(String numero) {
        String sql = "SELECT * FROM Habitaciones WHERE codigoHotel = '" + numero + "';";;
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res
                        = rs.getString("Habitaciones.codigoHotel") + ", "
                        + rs.getString("Habitaciones.numHabitaciones")
                        + ", " + rs.getString("Habitaciones.tipo") + ", "
                        + rs.getString("Habitaciones.valorHab");
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

//comprueba si existe la habitacion x en el hotel x
    static boolean compruebaExisteHabitacionHotel(String codigo, String habitaciones) {
        String sql = "SELECT * FROM Habitaciones WHERE numHabitaciones = '" + habitaciones
                + "' and codigoHotel = '" + codigo + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {

            if (rs.next()) {
                s = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

//
//     #     #  #     #  #######   #####   ######   #######  ######   #######   #####  
//     #     #  #     #  #        #     #  #     #  #        #     #  #        #     # 
//     #     #  #     #  #        #        #     #  #        #     #  #        #       
//     #######  #     #  #####     #####   ######   #####    #     #  #####     #####  
//     #     #  #     #  #              #  #        #        #     #  #              # 
//     #     #  #     #  #        #     #  #        #        #     #  #        #     # 
//     #     #   #####   #######   #####   #        #######  ######   #######   #####  
//                                                                                     
    static void huespedes() {
        int op;
        do {
            //menu huespedes
            System.out.println("\n");
            System.out.println("-----------------------------------\n"
                    + "||Menu Huespedes                    ||\n"
                    + "----------------------------------- \n"
                    + "|1. Crear Huesped                   |\n"
                    + "|2. Modificar Huespedes             |\n"
                    + "|3. Eliminar Huespedes              |\n"
                    + "|4. Consultas                       |\n"
                    + "|0. Volver                          |\n"
                    + "-----------------------------------");

            op = new Scanner(System.in).useLocale(Locale.US).nextInt();

            switch (op) {
                case 1:
                    //funcion que crea huespedes
                    crearHuespedes();
                    break;
                case 2:
                    //funcion que modifica huespedes
                    modificarHuespedes();
                    break;
                case 3:
                    //funcion que borra huespedes
                    borrarHuespedes();
                    break;
//                case 4:
//                    mostrarTodasHabitaciones();
//                    break;
                case 0:
                    System.out.println("Volviendo...\n\n");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (op != 0);
    }

// crear regirstro huesped
    private static void crearHuespedes() {
        try {
            System.out.println("/////////////////////////////////////////");
            //muestra los huespedes que existen hasta el momento creados
            mostrarTodosHuespedes();
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. dni: ");
            String dni = new Scanner(System.in).nextLine();
            //comprueba que el dni introducido existe, si existe volvera a pedir otro nuevo hasta que introduzca uno que no existe
            while (existeHuesped(dni) == true) {
                System.out.println("Ya existe este Huesped. Vuelve a introducirlo de nuevo");
                System.out.println("1. dni: ");
                dni = new Scanner(System.in).nextLine();
            }
            System.out.println("2. nombre: ");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("3. telefono: ");
            String telefono = new Scanner(System.in).nextLine();
            //comprueba que el telefono introducido no exista ya incluido en la BD,ya que un telefono no se puede repetir
            while (existeTelefonoHuesped(telefono) == true) {
                System.out.println("Ya existe este telefono registrado. Vuelve a introducirlo de nuevo");
                System.out.println("4. telefono: ");
                telefono = new Scanner(System.in).nextLine();
            }
            System.out.println("4. direccion: ");
            String direccion = new Scanner(System.in).nextLine();
            //crea el INSERT  de huespedes
            String sql = "INSERT Huespedes (dni, nombre, telefono, direccion) values"
                    + "('" + dni + "','" + nombre + "','" + telefono + "','" + direccion + "');";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

//modificar registro de huesped
    private static void modificarHuespedes() {
        System.out.println("/////////////////////////////////////////");
        //muestra los huespedes que existen hasta el momento creados
        mostrarTodosHuespedes();
        System.out.println("/////////////////////////////////////////");
        System.out.println("Elija el dni para modificar (primera fila) para modificar");
        String dni = new Scanner(System.in).nextLine();
        //comprobamos que el dni que queremos modificar exista, si no existe volveremos a pedir 
        // hasta que inserte uno que ya este creado
        while (!existeHuesped(dni) == true) {
            System.out.println("No existe este Huesped registrado. Vuelve a introducirlo de nuevo");
            System.out.println("1. dni Huesped: ");
            dni = new Scanner(System.in).nextLine();
        }
        try {
            System.out.println("2. Nuevo nombre: ");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("3. nuevo telefono: ");
            String telefono = new Scanner(System.in).nextLine();
            //comprueba que el telefono introducido no exista ya incluido en la BD,ya que un telefono no se puede repetir
            while (existeTelefonoHuesped(telefono) == true) {
                System.out.println("Ya existe este telefono registrado. Vuelve a introducirlo de nuevo");
                System.out.println("4. telefono: ");
                telefono = new Scanner(System.in).nextLine();
            }
            System.out.println("4. nueva direccion: ");
            String direccion = new Scanner(System.in).nextLine();
            //UODATE de huepedes con el dni que hayamos elegidos
            String sql = "UPDATE Huespedes SET nombre='" + nombre + "' ,telefono= '" + telefono
                    + "' ,direccion='" + direccion + "' WHERE dni= '" + dni + "';";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

// borrar registro huesped
    private static void borrarHuespedes() {
        try {
            System.out.println("/////////////////////////////////////////");
            //muesta todos los huespedes creado hasta el momento en la BD
            mostrarTodosHuespedes();
            System.out.println("/////////////////////////////////////////");
            System.out.println("Elija el dni para modificar (primera fila) para modificar");
            String dni = new Scanner(System.in).nextLine();
            //comprueba que si el dni que le pasamos esta creado o no, si no esta creado vovlemos a pedi hasta que este
            while (!existeHuesped(dni) == true) {
                System.out.println("No existe este Huesped registrado. Vuelve a introducirlo de nuevo");
                System.out.println("1. dni Huesped: ");
                dni = new Scanner(System.in).nextLine();
            }
            //confirmacion para borrar el huesped, si pulsamos S, si borrara, sino se abortará el proceso
            System.out.println("Estas seguro de querer eliminar el Hotel con codigo: " + dni + " (S/N)");
            String res = new Scanner(System.in).nextLine();
            res.equalsIgnoreCase(res);
            if (res.equalsIgnoreCase("s")) {
                //Borra el uspedes que le hayamos pasado como dni
                String sql = "DELETE FROM Huespedes where dni = '" + dni + "';";

                System.out.println("Borrado con exito.");
                try (Connection con = conecta()) {
                    ejecutaUpdate(con, sql);
                }
            } else {
                System.out.println("Misión abortada, no se borró el Hotel");
            }

        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    //compobacion existe telefono de huesped creado ya en BD
    static boolean existeTelefonoHuesped(String telefono) {
        String sql = "SELECT * FROM Huespedes WHERE telefono = '" + telefono + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");;
        }
        return s;
    }

    //comprueba si existe el DNI del huepedes en la BD
    static boolean existeHuesped(String dni) {
        String sql = "SELECT * FROM Huespedes WHERE dni = '" + dni + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

//muestra el registro completo de todaos los Hupedes con todos sus campos
    static boolean mostrarTodosHuespedes() {
        String sql = "SELECT * FROM Huespedes;";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res
                        = rs.getString("Huespedes.dni") + ", "
                        + rs.getString("Huespedes.nombre")
                        + ", " + rs.getString("Huespedes.telefono") + ", "
                        + rs.getString("Huespedes.direccion");
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

//
//                                            
//     #####     ##     ####    ####    ####  
//     #    #   #  #   #    #  #    #  #      
//     #    #  #    #  #       #    #   ####  
//     #####   ######  #  ###  #    #       # 
//     #       #    #  #    #  #    #  #    # 
//     #       #    #   ####    ####    ####  
//                                            
    static void pagos() {
        int op;
        do {
            // Menu Pago
            System.out.println("\n");
            System.out.println("-------------------------\n"
                    + "||Menu Pagos                     ||\n"
                    + "----------------------------------- \n"
                    + "|1. Crear Pagos                  |\n"
                    + "|2. Modificar Pagos              |\n"
                    + "|3. Borrar Pagos                 |\n"
                    + "|4. Mostrar pagos                |\n"
                    + "|0. Volver                       |\n"
                    + "---------------------------------");

            op = new Scanner(System.in).useLocale(Locale.US).nextInt();

            switch (op) {
                case 1:
                    //funcion que crea Pago
                    crearPago();
                    break;
                case 2:
                    //funcion que modifica un Pago
                    modificarPago();
                    break;
                case 3:
                    //funcion que borra un Pago
                    borrarPago();
                    break;
                case 4:
                    mostrarTodosPago();
                    break;
                case 0:
                    System.out.println("Volviendo...\n\n");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (op != 0);
    }

// funcion que regirstro de Pago
    private static void crearPago() {
        try {
//declaracion de variables que utilizaremos
            int camaSup = 0;
            int estadoPago = 0;
            String tipoPago = null;
            String tipoCama = null;
            int precio = 0;
            System.out.println("/////////////////////////////////////////");
            //muestra todos los pagos que estan creados en la BD
            mostrarTodosPago();
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. id de Pago: ");
            String id = new Scanner(System.in).nextLine();
            //comprueba el idPago,si existe pedira que introduzcas otro hasta que el que introduzcas no exista registrado
            while (existePago(id) == true) {
                System.out.println("Ya existe este registro de Pago. Vuelve a introducirlo de nuevo");
                System.out.println("1. id: ");
                id = new Scanner(System.in).nextLine();
            }
            System.out.println("2. numero de cuenta: ");
            String numero = new Scanner(System.in).nextLine();
            //comprueba que el numero de cuenta no exista registrado en la BD, si existe pedira otro hasta q no exista
            while (existeNumeroCuenta(numero) == true) {
                System.out.println("Ya existe este numero de cuenta registrado en Pago. Vuelve a introducirlo de nuevo");
                System.out.println("2. numero de cuenta: ");
                numero = new Scanner(System.in).nextLine();
            }
            //comfirmacion S o N, si queremos cama supletoria, si damos a N, CamaSUpletoria será false, y el tip ode cama sera null,
            // si damos S, cama supletoria será true, y debemos de elegir entre individual o doble, y depende de l cama que se elija
            // la individual vale 30, y l adoble 50
            System.out.println("3. Quieres utilizar cama Supletoria (S/N): ");
            String res = new Scanner(System.in).nextLine();
            while (!res.equalsIgnoreCase("s") && !res.equalsIgnoreCase("n")) {
                System.out.println("Selecciona correctamente S para afirmar, o N para negar. Vuelve a introducirlo de nuevo");
                res = new Scanner(System.in).nextLine();
            }
            if (res.equalsIgnoreCase("s")) {
                camaSup = 1;
                System.out.println("4. Tipo cama (individual/doble): ");
                tipoCama = new Scanner(System.in).nextLine();
                while (!tipoCama.equalsIgnoreCase("individual") && !tipoCama.equalsIgnoreCase("doble")) {
                    System.out.println("Selecciona un tipo de cama correcto. Vuelve a introducirlo de nuevo");
                    tipoCama = new Scanner(System.in).nextLine();
                }
                if (tipoCama.equalsIgnoreCase("individual")) {
                    precio = 30;
                }
                if (tipoCama.equalsIgnoreCase("doble")) {
                    precio = 50;
                }
            }
            //comfirmacion de Pago, S que esta pagado, N que no esta pagado aún,si es S, estado de pago sera true, si no sera false
            //mientras sea S, debemos de elegir el metodo de pago entre (tarjeta,transferencia o cheque)
            System.out.println("5. estado pago (S: pagado /N: no pagado): ");
            String estado = new Scanner(System.in).nextLine();
            while (!estado.equalsIgnoreCase("s") && !estado.equalsIgnoreCase("n")) {
                System.out.println("Selecciona correctamente S para afirmar, o N para negar. Vuelve a introducirlo de nuevo");
                estado = new Scanner(System.in).nextLine();
            }
            if (estado.equalsIgnoreCase("s")) {
                estadoPago = 1;

                System.out.println("6. Tipo pago (tarjeta, transferencia, cheque): ");
                tipoPago = new Scanner(System.in).nextLine();
                while (!tipoPago.equalsIgnoreCase("tarjeta") && !tipoPago.equalsIgnoreCase("transferencia") && !tipoPago.equalsIgnoreCase("cheque")) {
                    System.out.println("6. Tipo pago (tarjeta, transferencia, cheque):");
                    tipoPago = new Scanner(System.in).nextLine();
                }
            }

            if (estado.equalsIgnoreCase("n")) {
                estadoPago = 0;
            }
            //Muestra todos los huespedes creados
            System.out.println("/////////////////////////////////////////");
            mostrarTodosHuespedes();
            System.out.println("/////////////////////////////////////////");
            System.out.println("7. dni: ");
            String dni = new Scanner(System.in).nextLine();
            //comprueba si existe el huespedes por dni, si no existe volvera a pedir hasta que exista uno
            while (!existeHuesped(dni) == true) {
                System.out.println("No existe este Huesped registrado. Vuelve a introducirlo de nuevo");
                System.out.println("7. dni: ");
                dni = new Scanner(System.in).nextLine();
            }
            // INSERTE  de pago con todos los registros necesario
            String sql = "INSERT Pago (idPago, numeroCuenta, camaSupletoria,tipoCama,precioCamaSupletoria,estadoPago,tipoPago,dniHuespedes) values"
                    + "('" + id + "','" + numero + "','" + camaSup + "','" + tipoCama + "','" + precio + "','" + estadoPago + "','" + tipoPago + "','" + dni + "');";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }

        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

//modificar registro de Pagos
    private static void modificarPago() {
        try {
            //declaracion de variables
            int camaSup = 0;
            int estadoPago = 0;
            String tipoPago = null;
            String tipoCama = null;
            int precio = 0;
            System.out.println("/////////////////////////////////////////");
            //muestra todos los pagos que estan creados en la BD
            mostrarTodosPago();
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. Selecciona un id de Pago para modificar sus datos: ");
            String id = new Scanner(System.in).nextLine();
            //comprueba el idPago,no existe pedira que introduzcas otro hasta que el que introduzcas exista registrado
            while (!existePago(id) == true) {
                System.out.println("No existe este registro en Pago. Vuelve a introducirlo de nuevo");
                System.out.println("1. id Pago: ");
                id = new Scanner(System.in).nextLine();
            }
            System.out.println("2. numero de cuenta: ");
            String numero = new Scanner(System.in).nextLine();
            //comprueba que el numero de cuenta no exista registrado en la BD, si existe pedira otro hasta q no exista
            while (existeNumeroCuenta(numero) == true) {
                System.out.println("Ya existe este numero de cuenta registrado en Pago. Vuelve a introducirlo de nuevo");
                System.out.println("2. numero de cuenta: ");
                numero = new Scanner(System.in).nextLine();
            }
            //comfirmacion S o N, si queremos cama supletoria, si damos a N, CamaSUpletoria será false, y el tip ode cama sera null,
            // si damos S, cama supletoria será true, y debemos de elegir entre individual o doble, y depende de l cama que se elija
            // la individual vale 30, y l adoble 50
            System.out.println("3. Quieres utilizar cama Supletoria (S/N): ");
            String res = new Scanner(System.in).nextLine();
            while (!res.equalsIgnoreCase("s") && !res.equalsIgnoreCase("n")) {
                System.out.println("Selecciona correctamente S para afirmar, o N para negar. Vuelve a introducirlo de nuevo");
                res = new Scanner(System.in).nextLine();
            }
            if (res.equalsIgnoreCase("s")) {
                camaSup = 1;
                System.out.println("4. Tipo cama (individual/doble): ");
                tipoCama = new Scanner(System.in).nextLine();
                while (!tipoCama.equalsIgnoreCase("individual") && !tipoCama.equalsIgnoreCase("doble")) {
                    System.out.println("Selecciona un tipo de cama correcto. Vuelve a introducirlo de nuevo");
                    tipoCama = new Scanner(System.in).nextLine();
                }
                if (tipoCama.equalsIgnoreCase("individual")) {
                    precio = 30;
                }
                if (tipoCama.equalsIgnoreCase("doble")) {
                    precio = 50;
                }
            }
        //comfirmacion de Pago, S que esta pagado, N que no esta pagado aún,si es S, estado de pago sera true, si no sera false
            //mientras sea S, debemos de elegir el metodo de pago entre (tarjeta,transferencia o cheque)
            System.out.println("5. estado pago (S: pagado /N: no pagado): ");
            String estado = new Scanner(System.in).nextLine();
            while (!estado.equalsIgnoreCase("s") && !estado.equalsIgnoreCase("n")) {
                System.out.println("Selecciona correctamente S para afirmar, o N para negar. Vuelve a introducirlo de nuevo");
                estado = new Scanner(System.in).nextLine();
            }
            if (estado.equalsIgnoreCase("s")) {
                estadoPago = 1;

                System.out.println("6. Tipo pago (tarjeta, transferencia, cheque): ");
                tipoPago = new Scanner(System.in).nextLine();
                while (!tipoPago.equalsIgnoreCase("tarjeta") && !tipoPago.equalsIgnoreCase("transferencia") && !tipoPago.equalsIgnoreCase("cheque")) {
                    System.out.println("6. Tipo pago (tarjeta, transferencia, cheque):");
                    tipoPago = new Scanner(System.in).nextLine();
                }
            }

            if (estado.equalsIgnoreCase("n")) {
                estadoPago = 0;
            }
            //Muestra todos los huespedes creados
            System.out.println("/////////////////////////////////////////");
            mostrarTodosHuespedes();
            System.out.println("/////////////////////////////////////////");
            System.out.println("7. dni: ");
            String dni = new Scanner(System.in).nextLine();
            //comprueba si existe el huespedes por dni, si no existe volvera a pedir hasta que exista uno
            while (!existeHuesped(dni) == true) {
                System.out.println("No existe este Huesped registrado. Vuelve a introducirlo de nuevo");
                System.out.println("7. dni: ");
                dni = new Scanner(System.in).nextLine();
            }
            // crea el UPDATE de Pagos con sus registros actualizados segun hayamos pasado
            String sql = "UPDATE Pago SET numeroCuenta= '" + numero
                    + "' ,camaSupletoria='" + camaSup
                    + "' ,tipoCama='" + tipoCama
                    + "' ,precioCamaSupletoria= '" + precio
                    + "' ,estadoPago='" + estadoPago
                    + "' ,tipoPago='" + tipoPago
                    + "' ,dniHuespedes='" + dni
                    + "' WHERE idPago= '" + id + "';";

            try (Connection con = conecta()) {
                ejecutaUpdate(con, sql);
            }
        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }
//funcio que borra un Pago
    private static void borrarPago() {
        try {
            System.out.println("/////////////////////////////////////////");
            //muestra todos los apgos con todos sus registros creado hasta el momento
            mostrarTodosPago();
            System.out.println("/////////////////////////////////////////");
            System.out.println("1. Selecciona un id de Pago para modificar sus datos: ");
            String id = new Scanner(System.in).nextLine();
            //comprueba que el idPago exista, si n oexiste vuelve a pedir hasta que se introduzca uno que exista
            while (!existePago(id) == true) {
                System.out.println("No existe este registro en Pago. Vuelve a introducirlo de nuevo");
                System.out.println("1. id Pago: ");
                id = new Scanner(System.in).nextLine();
            }
            System.out.println("/////////////////////////////////////////");
            //muestra el huesped con todos sus campos que hayamos sleccionado a borrar
            mostrarPagoDefinido(id);
            System.out.println("/////////////////////////////////////////");
            //confirmacion de borrar un Pago, mientras sea S se borrará, si no se abortará preoceso
            System.out.println("Estas seguro de querer eliminar el Pago con codigo: " + id + " (S/N)");
            String res = new Scanner(System.in).nextLine();
            res.equalsIgnoreCase(res);
            if (res.equalsIgnoreCase("s")) {
                //borra un Pago que le pasamos con idPago
                String sql = "DELETE FROM Pago where idPago = '" + id + "';";

                System.out.println("Borrado con exito.");
                try (Connection con = conecta()) {
                    ejecutaUpdate(con, sql);
                }
            } else {
                System.out.println("Misión abortada, no se borró el Hotel");

            }

        } catch (SQLException ex) {

            System.out.println("Error SQL");
        }
    }

    //compobacion si existe numero cuenta en Pago
    static boolean existeNumeroCuenta(String numero) {
        String sql = "SELECT * FROM Pago WHERE numeroCuenta = '" + numero + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");;
        }
        return s;
    }

    //comprueba si existe huesped por DNI
    static boolean existePago(String id) {
        String sql = "SELECT * FROM Pago WHERE idPago = '" + id + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            if (rs.next()) {
                s = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }
//muestra el registro completo de toos los Pagos
    static boolean mostrarTodosPago() {
        String sql = "SELECT * FROM Pago;";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res
                        = rs.getString("Pago.idPago") + ", "
                        + rs.getString("Pago.numeroCuenta") + ", "
                        + rs.getString("Pago.camaSupletoria") + ", "
                        + rs.getString("Pago.tipoCama") + ", "
                        + rs.getString("Pago.precioCamaSupletoria") + ", "
                        + rs.getString("Pago.estadoPago") + ", "
                        + rs.getString("Pago.tipoPago") + ", "
                        + rs.getString("Pago.dniHuespedes") + ", ";
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }
//funcio nque muestra todos lso campos del idPago que le pasemos como parametro
    static boolean mostrarPagoDefinido(String id) {
        String sql = "SELECT * FROM Pago WHERE idPago = '" + id + "';";
        boolean s = false;
        Connection con = conecta();
        ResultSet rs = ejecuta(con, sql);
        try {
            while (rs.next()) {
                String res
                        = rs.getString("Pago.idPago") + ", "
                        + rs.getString("Pago.numeroCuenta") + ", "
                        + rs.getString("Pago.camaSupletoria") + ", "
                        + rs.getString("Pago.tipoCama") + ", "
                        + rs.getString("Pago.precioCamaSupletoria") + ", "
                        + rs.getString("Pago.estadoPago") + ", "
                        + rs.getString("Pago.tipoPago") + ", "
                        + rs.getString("Pago.dniHuespedes") + ", ";
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
        }
        return s;
    }

}
