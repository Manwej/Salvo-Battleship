package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String password;
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public Player(){}
    public Player(String user, String password){
        this.userName=user;
        this.password=password;
    }
    public void addGamePlayer(GamePlayer gameplayer) {
        gamePlayers.add(gameplayer);
    }
    @JsonIgnore
    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }

    public long getPlayerId(){
        return id;
    }
    public String getUserName(){
        return userName;
    }
    @JsonIgnore
    public String getUserPassword(){
        return password;
    }
    public void setUserName(String user){
        this.userName=user;
    }
    public void setPlayerPassword(String password){
        this.password=password;
    }

}
