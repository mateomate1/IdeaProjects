package app;

import java.time.LocalDate;

public class Empleado {
    Integer ID;
    String nombre, cargo;
    Double salario;
    LocalDate fContratacion;
    Departamento departamento;

    public Empleado() {
    }

    public Empleado(Integer ID, String nombre, String cargo, Double salario, LocalDate fContratacion, Departamento departamento) {
        this.ID = ID;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.fContratacion = fContratacion;
        this.departamento = departamento;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getfContratacion() {
        return fContratacion;
    }

    public void setfContratacion(LocalDate fContratacion) {
        this.fContratacion = fContratacion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }



    @Override
    public String toString() {
        return "Empleados{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", fContratacion=" + fContratacion +
                ", departamento=" + departamento +
                '}';
    }
}
