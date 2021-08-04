package com.example.reactivemapandflatmaps;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootTest
class ReactiveMapAndFlatMapsApplicationTests{

    private Flux<SpaceShip> flux() {
        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        )
        .delayElements(Duration.ofSeconds(1));

    }
    @Test
    void tryFlatMaps() throws InterruptedException{
        System.out.println("Start of test");
        Flux<SpaceShip> flux = flux();

        //flux.log gives good insight to what's going on with events
        flux.log("ben.flux.").map(ship -> {  //for each ship in the flux set the id to -1
            ship.setId(-1);
            return ship;
        }).flatMap( ship -> {
            return Flux.just(ship, new SpaceShip(-100, ship.getName() + "_LARGE", ship.getCrew() * 2));
                })

                .subscribe(ship -> {  //nothing will happen if we don't subscribe
            System.out.println(String.format("Ship: %s", ship));
        });

        System.out.println("Start of test");
        Thread.sleep(10000);
    }
}
