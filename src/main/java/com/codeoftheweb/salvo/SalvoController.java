package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository repo;
    @Autowired
    private GamePlayerRepository gPrepo;
    @Autowired
    private ShipRepository shipRepository;

    @RequestMapping("/games")
    public List<Map> getAll() {
        List<Map> allGames = new ArrayList<>();
        repo.findAll().forEach(oneGame -> {
                    allGames.add(makeGameDTO(oneGame));
                }
        );
        return allGames;
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getGameId());
        dto.put("date", game.getGameDate());
        dto.put("gamePlayer", game.getGamePlayers().stream().map(gamePlayer ->
                gamePlayer.getPlayer()
        ));
        return dto;
    }

    /*@RequestMapping("/game_view")
    public List<Game> getAllGames() {
        return repo.findAll();
    }*/
    @RequestMapping("/game_view/{Id}")
    public Optional<GamePlayer> getGamePlayersAll(@PathVariable long Id) {
        Optional<GamePlayer> gameplayer = gPrepo.findById(Id);

        return gameplayer;
    }
    /*
    @RequestMapping("/game_view/{Id}")
    public Map<String, Object> getGamePlayersAll(@PathVariable long Id) {
        Optional<GamePlayer> gameplayer = gPrepo.findById(Id);
        Map<String, Object> gameView =new LinkedHashMap<>();
        if (gameplayer != null) {
            gameView.put("Salvo", getSalvoForController(gameplayer.get()));
            gameView.put("game", gameplayer.get().getGame());
            gameView.put("ships", gameplayer.get().getShips());
            gameView.put("player", gameplayer.get().getPlayer());
        }
        return gameView;
    }

   /* private List<Map> getSalvoForController(GamePlayer gamePlayer) {
        List<Map> allSalvosFromGamePlayer = new ArrayList<>();
        gamePlayer.getSalvos().forEach(oneSalvo->{
            Map<Long, Object> slvWhut = new LinkedHashMap<Long, Object>();
            slvWhut.put(oneSalvo.getGamePlayer().getGamePlayerId(), oneSalvo.getLocations());
            Map<Integer, Object> slvDto = new LinkedHashMap<Integer, Object>();
            slvDto.put(oneSalvo.getTurnNumber(), slvWhut);
            allSalvosFromGamePlayer.add(slvDto);
        });
        return allSalvosFromGamePlayer;
    }*/
}


