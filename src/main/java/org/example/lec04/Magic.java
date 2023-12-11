package org.example.lec04;


import javax.persistence.*;

@Entity
@Table(name = "test.magic")
public class Magic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "название")
    private String name;

    @Column(name = "повреждение")
    private int damage;

    @Column(name = "атака")
    private int attBonus;

    @Column(name = "броня")
    private int def;

    public Magic(String name, int damage, int attBonus, int def) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.def = def;
    }

    public Magic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttBonus() {
        return attBonus;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public int getId() {
        return id;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", attBonus=" + attBonus +
                ", def=" + def +
                '}';
    }
}
