package com.codeoftheweb.salvo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private long id;
    private Date date;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public void addGamePlayer(GamePlayer gameplayer) {
        gamePlayers.add(gameplayer);
    }
@JsonIgnore
    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }
    public Game(){}
    public Game(Date date){
        this.date=date;
    }
    public long getGameId(){
        return id;
    }
    public Date getGameDate(){
        return date;
    }
    public void setGameDate(Date date){
        this.date=date;
    }


}
