package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Embeddable
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String type;

    @ElementCollection
    @Column(name="ShipLocation")
    private List<String> locations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    private long turnNumber;

    public Ship() {}
    public Ship(String type, List<String> locations, GamePlayer gameplayer){
        this.type= type;
        this.locations= locations;
        this.gamePlayer=gameplayer;
    }

    public String getShipType(){
        return type;
    }
    public long getId(){
        return id;
    }
   /* @JsonIgnore
    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }*/
    public void setShipType(String type) {
        this.type = type;
    }
    public List<String> getLocation () {
        return locations;
    }

}