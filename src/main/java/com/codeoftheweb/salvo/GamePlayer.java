package com.codeoftheweb.salvo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.*;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;
    private Date date;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Salvo> salvos = new HashSet<>();


    public GamePlayer(){}
    public GamePlayer(Game game, Player player, Date date){
        this.game= game;
        this.player=player;
        this.date=date;
    }
    public long getGamePlayerId(){return id;}
    public Date getCreateDate(){return date;}

    public Game getGame() {
        return game;
    }
    public Set<Ship> getShips() {
        return ships;
    }
    public Player getPlayer() {
        return player;
    }
 // test only for sake of typing code
    public Set<Salvo> getSalvos() {
        return salvos;
    }
}
