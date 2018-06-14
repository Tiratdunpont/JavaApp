/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.biblioteca;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author IRA_a
 */
public class GestorBiblioteca {

    List<ElementBiblioteca> Elements = new ArrayList<ElementBiblioteca>();
    List<Usuari> Users = new ArrayList<Usuari>();
    List<String> Strel = new ArrayList<String>();
    Usuari Aux;
    int indexElements = 0;

    public static void main(String[] args) throws ClassNotFoundException {
        GestorBiblioteca prog = new GestorBiblioteca();
        prog.inici();
    }

    private void inici() throws ClassNotFoundException {
        Connection conn = null;
        Biblioteca b = new Biblioteca();
        b.setVisible(true);
        /*try {

            if (!Create(conn)) {
                Select(conn);
            }
//            b.initDades(Elements);
//            b.addWindowListener(new WindowAdapter() {
//                public void windowClosing(WindowEvent e) {
//                    Elements = b.Elements;
//                }
//            });
            b.setVisible(true);

        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
        } finally {
            while (b.isActive());
            if (conn != null) {
                try {
                    Recrear(conn);
                    System.out.println(Elements.size());
                    for (int i = 0; i < Elements.size(); i++) {
                        System.out.println(Elements.get(i).toDB());
                        Insert(conn, Elements.get(i));
                    }
                    conn.close();
                    System.out.println("Database connection terminated");
                } catch (Exception e) {
                     ignore close errors  }
            }
        }*/
    }

    public void Delete(Connection conn, int index) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            s.executeUpdate("DELETE FROM Elements WHERE codi = " + index + ";");
            s.close();
            System.out.println("Rows were deleted");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void DeleteUser(Connection conn, String DNI) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE DNI = (?);");
            stmt.setString(1, DNI);
            System.out.println("Rows were deleted");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Recrear(Connection conn) {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS Elements");
            s.executeUpdate("DROP TABLE IF EXISTS Users");
            s.executeUpdate("CREATE TABLE Elements (" + "codi INT UNSIGNED NOT NULL AUTO_INCREMENT," + "PRIMARY KEY (codi)," + "tipus CHAR(40) NOT NULL, titol CHAR(100) NOT NULL, editorial CHAR(40) NOT NULL, anyadquisicio CHAR(40) NOT NULL, numExemplar INT NOT NULL, dataprestec CHAR(40),  personaprestada CHAR(40), prestat CHAR(1), baixa CHAR(1), ISBN CHAR(40))");
            s.executeUpdate("CREATE TABLE Users (" + "codi INT UNSIGNED NOT NULL AUTO_INCREMENT," + "PRIMARY KEY (codi)," + "Nom CHAR(40) NOT NULL, DNI CHAR(100) NOT NULL, edat CHAR(40) NOT NULL, llogat CHAR(1), sancionat CHAR(1))");
            s.close();
            System.out.println("Base Refeta");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public void Insert(Connection conn, ElementBiblioteca element) {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            int count;
            s.executeUpdate(element.toDB());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public void InsertUser(Connection conn, Usuari Usuari) {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            int count;
            s.executeUpdate(Usuari.toDB());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public String IntroduirString(String t) {
        Scanner scan = new Scanner(System.in);
        String n = "";
        boolean acabat = false;
        double a;
        while (!acabat) {
            System.out.print("Introduïu " + t + ": ");
            n = scan.nextLine();
            acabat = true;
        }
        return n;
    }

    public boolean CheckTable(Connection conn, String nom) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet rs = dbm.getTables(null, null, nom, null);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CreateElements(Connection conn) {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            if (!CheckTable(conn, "Elements")) {
                s.executeUpdate("CREATE TABLE Elements (" + "codi INT UNSIGNED NOT NULL AUTO_INCREMENT," + "PRIMARY KEY (codi)," + "tipus CHAR(40) NOT NULL, titol CHAR(100) NOT NULL, editorial CHAR(40) NOT NULL, anyadquisicio CHAR(40) NOT NULL, numExemplar INT NOT NULL, dataprestec CHAR(40),  personaprestada CHAR(40), prestat CHAR(1), baixa CHAR(1), ISBN CHAR(40))");
                s.close();
                TancarConnexio(conn);
                return true;
            }
            s.close();
            TancarConnexio(conn);
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
        return false;
    }

    public boolean CreateUsers(Connection conn) {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            if (!CheckTable(conn, "Users")) {
                s.executeUpdate("CREATE TABLE Users (" + "codi INT UNSIGNED NOT NULL AUTO_INCREMENT," + "PRIMARY KEY (codi)," + "Nom CHAR(40) NOT NULL, DNI CHAR(100) NOT NULL, edat CHAR(40) NOT NULL, llogat CHAR(1), sancionat CHAR(1))");
                s.close();
                TancarConnexio(conn);
                return true;
            }
            s.close();
            TancarConnexio(conn);
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
        return false;
    }

    public void SelectElements(Connection conn) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM Elements");
            ResultSet rs = s.getResultSet();
            int count = 0;

            while (rs.next()) {
                boolean prestat = false, baixa = false;
                if (Integer.parseInt(rs.getString("prestat")) == 1) {
                    prestat = true;
                }
                if (Integer.parseInt(rs.getString("baixa")) == 1) {
                    baixa = true;
                }
                ElementBiblioteca a = new ElementBiblioteca(rs.getString("tipus"), rs.getInt("codi"), rs.getString("titol"), rs.getString("editorial"), rs.getString("anyadquisicio"), rs.getInt("numExemplar"), rs.getString("dataprestec"), rs.getString("personaprestada"), prestat, baixa, rs.getString("ISBN"));
                Elements.add(a);
                ++count;
            }
            rs.close();
            s.close();
            System.out.println(count + " registres carregats");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public void EpicSelect(List<ElementBiblioteca> Elements, List<Usuari> Users) {
        List<String> index = new ArrayList<String>();
        for (int i = 0; i < Elements.size(); i++) {
            String n = "";
            for (int j = 0; j < Users.size(); j++) {
                if (Users.get(j).getDNI().equals(Elements.get(i).getPersonaprestada())) {
                    if (index.size() == 0){
                        n = Elements.get(i).getTitol() + "ln";
                        index.add(Users.get(j).getDNI());
                        Strel.add(n);
                        System.out.println(i+" "+j+" "+Users.get(j).getDNI()+" "+n);
                    }
                    else{
                        boolean bono = false;
                        for (int r = 0; r < index.size(); r++) {
                            if (Users.get(j).getDNI().equals(index.get(r))) {
                                n= Strel.get(r)+""+Elements.get(i).getTitol()+ "ln";
                                Strel.set(r, n);
                                bono = true;
                            }
                        }
                        if (!bono){
                            index.add(Users.get(j).getDNI());
                            n = Elements.get(i).getTitol() + "ln";
                            Strel.add(n);
                        }
                    }
                }
                else{
                    index.add(Users.get(j).getDNI());
                    Strel.add(n);
                }
            }
            
        }
    }

    public void SelectUsers(Connection conn) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM Users");
            ResultSet rs = s.getResultSet();
            int count = 0;

            while (rs.next()) {
                boolean prestat = false, baixa = false;
                if (Integer.parseInt(rs.getString("sancionat")) == 1) {
                    prestat = true;
                }
                if (Integer.parseInt(rs.getString("llogat")) == 1) {
                    baixa = true;
                }
                Usuari a = new Usuari(rs.getString("Nom"), rs.getString("DNI"), Integer.parseInt(rs.getString("edat")), prestat, baixa);
                Users.add(a);
                ++count;
            }
            rs.close();
            s.close();
            System.out.println(count + " registres carregats");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public boolean SelectUser(Connection conn, String DNI) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            String sql = "SELECT * FROM Users WHERE DNI=(?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, DNI);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            int count = 0;

            if (rs.next()) {
                boolean prestat = false, baixa = false;
                if (Integer.parseInt(rs.getString("sancionat")) == 1) {
                    prestat = true;
                }
                if (Integer.parseInt(rs.getString("llogat")) == 1) {
                    baixa = true;
                }
                Usuari a = new Usuari(rs.getString("Nom"), rs.getString("DNI"), Integer.parseInt(rs.getString("edat")), prestat, baixa);
                Aux = a;
                ++count;
                TancarConnexio(conn);
                return true;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
        return false;
    }

    public void Update(Connection conn, ElementBiblioteca el) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement s = conn.createStatement();
            s.executeUpdate(el.Update());
            s.close();
            System.out.println("Rows were updated");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public void UpdateUser(Connection conn, Usuari el, String DNI) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/GestorBiblioteca";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            int prestats = 0;
            int baixats = 0;
            if (el.isLlogat()) {
                prestats = 1;
            }
            if (el.isSancionat()) {
                baixats = 1;
            }
            PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET `Nom` = '"+el.getNom()+"', `DNI` = '"+el.getDNI()+"', `edat` = '"+el.getEdat()+"', `llogat` = '"+prestats+"', `sancionat` = '"+baixats+"' WHERE `DNI` = (?) ;");
            stmt.setString(1, DNI);
            System.out.println(""+stmt);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Rows were updated");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TancarConnexio(conn);
    }

    public int IntroduirNum() {
        Scanner scan = new Scanner(System.in);
        int f = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print("Introduïu id: ");
            if (scan.hasNextInt()) {
                f = scan.nextInt();
                correcte = true;
            } else {
                System.out.println("Parametres incorrectes");
            }
            scan.nextLine();
        }

        return f;
    }

    public void TancarConnexio(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection terminated");
            } catch (Exception e) {
                /* ignore close errors */ }
        }
    }
}
