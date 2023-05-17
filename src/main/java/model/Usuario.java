package model;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    private int tipoUsuario;
    private String sexo;

    public Usuario(String nombreUsuario, String password, String email, String nombre, String apellidos, int tipoUsuario, String sexo) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoUsuario = tipoUsuario;
        this.sexo = sexo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}