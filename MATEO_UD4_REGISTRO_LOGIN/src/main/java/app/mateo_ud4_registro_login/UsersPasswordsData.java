package app.mateo_ud4_registro_login;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsersPasswordsData implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MAX_CHARS = 10, MIN_CHARS = 5, MAX_USERS = 10;
    public static final String
    USABLE_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // Letras mayúsculas
            "abcdefghijklmnopqrstuvwxyz" + // Letras minúsculas
            "0123456789" + // Números
            "!#$%&'()*+,-./:;<=>?@[]^_`{|}~", // Caracteres especiales permitidos;
    usersDataFile = "UsersData.dat";

    // private char[][] user = new char[2][10];  Al ser un char de 2x10 solo permite 2 campos(usuario y contraseña de 10 caracteres)
    //private List<char[][]> users = new ArrayList<>();
    private Map<String, String> users = UsersPasswordsData.recoverUsers();
    private String user;

    public UsersPasswordsData() throws IOException, ClassNotFoundException {
        users = getUsers();
        user = null;
    }

    public static boolean addUser(char[][] user) throws FileNotFoundException, IOException, ClassNotFoundException {
        boolean added = true;
        Map<String, String> users = UsersPasswordsData.recoverUsers();
        if (users == null)
            System.out.println("Esta vacio");
        System.out.println("Usuarios recuperados");
        if (users.size() < MAX_USERS) { // Comprobar que no hay mas de 10 objetos
            if (user.length == 2) { // Comprobar que cada objeto contiene 2 campos(usuario-contraseña)
                for (char[] row : user) { // Iterar sobre las filas (usuario/contraseña)
                    if(!added) break; // Parar si ya se ha hecho break en el for anidado
                    if (row.length >= MIN_CHARS && row.length <= MAX_CHARS){ // Comprobar que el usuario/cotraseña contenga de 5-10 caracteres
                        for (char c : row){ // Iterar sobre el usuario/contraseña
                            if (USABLE_CHARS.indexOf(c) == -1) { // Comprobar cuando contiene caracteres no validos
                                added = false;
                                break; // Dejar de iterar
                            }
                        }
                    } else { // Cambiar added a false cuando el usuario/cotraseña no este en el rango de 5-10 caracteres y dejar de iterar
                        added = false;
                        break;
                    }
                }
            } else added = false;
        } else added = false;

        if (added) {
            users.put(new String(user[0]),new String(user[1]));
            FileOutputStream fileOut = new FileOutputStream(usersDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        }
        return added;
    }

    public static Map<String, String> recoverUsers() throws IOException, ClassNotFoundException {
        Map<String, String> salida = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream(usersDataFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            UsersPasswordsData data = (UsersPasswordsData) in.readObject();
            salida = data.getUsers();
            in.close();
            fileIn.close();
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        return salida;
    }

    public Map<String, String> getUsers() throws IOException, ClassNotFoundException {
        users = recoverUsers();
        return users;
    }

    public int access(String user, String password) throws IOException, ClassNotFoundException {
        int salida = 0; // Salida en caso de existir el usuario pero estar mal la contraseña
        users = recoverUsers(); // Actualiza los valores(Necesario en caso de poder abrir varias veces la misma app, lo cual cambuaria el valor sin ser notado
        if(users.containsKey(user)){ // Verifica si existe el usuario
            if (password.equals(users.get(user))) {
                salida = 1; // Salida en caso de existir el usuario y la contraseña ser correcta
                this.user = user;
            }
        } else salida = -1; // Salida en el caso de no existir el usuario
        return salida;
    }
}
