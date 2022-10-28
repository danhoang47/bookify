/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author ADMIN
 */
public class AmenityDTO {
    private String id;
    private String name;
    private String icon;
    private String type;

    public AmenityDTO() {
    }

    public AmenityDTO(String id, String name, String icon, String type) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setType(String type) {
        this.type = type;
    }
}
