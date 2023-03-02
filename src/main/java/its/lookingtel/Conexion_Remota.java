/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.LookingTel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Guest Mode
 */
public class Conexion_Remota {

    public static void main(String args[]) {

        final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = System.getenv("MYSQL_CLEVERCLOUD_URL");
        final String DB_USERNAME = System.getenv("MYSQL_CLEVERCLOUD_USERNAME");
        final String DB_PASSWORD = System.getenv("MYSQL_CLEVERCLOUD_PASSWORD");
        Connection conn = null;
     
        
        

        try {

            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (conn == null) {
                System.out.print("Error on connection");
                return;
            }
            System.out.println("Succesfully connected :)");
            System.out.println();

            /*
            
            / TABLA UBICACION /
            
           CREATE TABLE UBICATION (Id INT(2) AUTO_INCREMENT, Pais VARCHAR(30), Estado VARCHAR(30), Ciudad VARCHAR(30) UNIQUE, PRIMARY KEY (Id)) AUTO_INCREMENT=1
           INSERT INTO UBICACION (Pais,Estado,Ciudad) VALUES ('x','x','x')
 
            / TABLA USUARIOS /
            
           "CREATE TABLE USERS (\n" +
"    Id INT(2) AUTO_INCREMENT PRIMARY KEY,\n" +
"    Id_Ubicacion INT(2),\n" +
"    Nombre VARCHAR(45),\n" +
"    Telefono VARCHAR(10),\n" +
"    Email VARCHAR(50),\n" +
"    Fecha_Nacimiento DATE,\n" +
"    Edad INT(2),\n" +
"    Direccion VARCHAR(65),\n" +
"    Sexo VARCHAR(1),\n" +
"    Fecha_Registro DATE,\n" +
"    Contraseña VARCHAR(15),\n" +
"    CONSTRAINT FK_USERS_UBICATION FOREIGN KEY (Id_Ubicacion) REFERENCES UBICATION(Id) ON DELETE CASCADE\n" +
") AUTO_INCREMENT=1;"
    
            
   INSERT INTO USERS (Id_Ubicacion, Nombre, Telefono, Email, Fecha_Nacimiento, Edad, Direccion, Sexo, Fecha_Registro, Contraseña) \n" +
"VALUES (3, 'John Doe', '1234567890', 'johndoe@example.com', '2023-02-24', 30, '123 Main St', 'M', '2023-02-24', 'mypassword');
            
    SELECT Id, Id_Ubicacion, Nombre, Telefono, Email, DATE_FORMAT(Fecha_Nacimiento, '%d/%m/%Y') as Fecha_Nacimiento, Edad, Direccion, Sexo, DATE_FORMAT(Fecha_Registro, '%d/%m/%Y') as Fecha_Registro, Contraseña FROM USERS;
            
            
     / TABLA CONDOMINIOS /
            
            
     CREATE TABLE CONDOMINIOS(
    Id INT(2) AUTO_INCREMENT PRIMARY KEY, 
    Id_Ubicacion INT(2),
    Nombre VARCHAR(45),
    CIF VARCHAR(10),
    Puntaje INT(3),
    Fecha_Registro DATE, 
    No_Habitaciones INT(1),
    Direccion VARCHAR(65),
    Precio_x_Noche INT(5),
    Servicios_Incluidos VARCHAR(100),
    Imagen_Lugar VARCHAR(500),
    Status BOOLEAN NOT NULL DEFAULT 1,
    CONSTRAINT FK_CONDOMINIOS_UBICATION FOREIGN KEY (Id_Ubicacion) REFERENCES UBICATION(Id) ON DELETE CASCADE
) AUTO_INCREMENT=1;

            INSERT INTO CONDOMINIOS (Id_Ubicacion, Nombre, CIF,Puntaje, Fecha_Registro,No_Habitaciones, Direccion,Precio_x_Noche, Servicios_Incluidos,Imagen_Lugar) VALUES (1, 'Sayulita', 'AJDUW820DK',45, '2023-02-23', 9, '123 Main St', 5000, 'Agua,Gas,Luz', 'http://google.com');");
           
            
  / TABLA RESERVACIONES /
        
            
   CREATE TABLE RESERVACIONES (\n" +
"    Id INT(2) AUTO_INCREMENT PRIMARY KEY, \n" +
"    Id_Condominio INT(2) UNIQUE,\n" +
"    Id_Usuario INT(2),\n" +
"    No_Personas INT(2),\n" +
"    Dias_Estadia INT(2),\n" +
"    Fecha_Reservacion DATETIME, \n" +
"    Fecha_Llegada DATETIME,\n" +
"    Fecha_Partida DATETIME,\n" +
"    Costo_Total INT(8),\n" +
"    CONSTRAINT FK_RESERVACION_CONDOMINIO FOREIGN KEY (Id_Condominio) REFERENCES CONDOMINIOS(Id) ON DELETE CASCADE,\n" +
"    CONSTRAINT FK_RESERVACION_USUARIO FOREIGN KEY (Id_Usuario) REFERENCES USERS(Id) ON DELETE CASCADE\n" +
") AUTO_INCREMENT=1;
            
   
INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);");
                         
            
     
  
            / TABLA ADMINISTRADORES /
            
      
    "CREATE TABLE ADMINISTRADORES ( \n" +
                   "Id INT(2) AUTO_INCREMENT PRIMARY KEY, \n"+
                   "Nombre VARCHAR (45),\n"+
                   "Email VARCHAR(50),\n" +
                  "Contraseña VARCHAR(15)) AUTO_INCREMENT=1;" 
            
            
            INSERT INTO ADMINISTRADORES (Nombre,Email,Contraseña) \n"
                    + " VALUES ('Jose Campos','enriquehernandez.pereyra@gmail.com','CAHE980311')
            
            
             */
           // Statement st = conn.createStatement();

          //  st.execute("""
                   //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
                   //    """);            
            //ResultSet rs = st.executeQuery("");

            //rs.close();
          //  st.close();
            //conn.close();
            // ResultSetMetaData rsmd = rs.getMetaData();
            //  String output = rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + "\n";

            //    while (rs.next()) {
            //    output += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " ";
            //   }
            //  System.out.print(output);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print(ex);
        }
    }

}
