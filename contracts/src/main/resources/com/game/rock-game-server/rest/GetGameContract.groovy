package com.game.rockgameserver.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a game matching the Id"
    request{
        method GET()
        url $(c(~/\/game\/[0-9]*/), p("/game/1"))
    }
    response {
        body([
            id: $(p(positiveInt()), c(1)),
            rounds: $(p(positiveInt()), c(1)),
            lastRoundResult: $(p(regex("[WIN|DRAW|LOOSE]+")), c("WIN")),
            players: [
                [
                    id: $(p(positiveInt()), c(1)),
                    name: $(p(anyNonEmptyString()),c("player1")),
                    lastMovement: $(p(regex("ROCK|SCISSORS|PAPER")), c("ROCK")),
                    wins: $(p(anyInteger()), c(0)),
                    draws: $(p(anyInteger()), c(1))
                ],
                [
                    id: $(p(positiveInt()), c(2)),
                    name: $(p(anyNonEmptyString()),c("player2")),
                    lastMovement: $(p(regex("ROCK|SCISSORS|PAPER")), c("ROCK")),
                    wins: $(p(anyInteger()), c(0)),
                    draws: $(p(anyInteger()), c(1))
                ]
            ],
            gameStats: [
                WINS_PLAYER_1: $(p(anyInteger()), c(0)),
                WINS_PLAYER_2: $(p(anyInteger()), c(0)),
                DRAWS: $(p(anyInteger()), c(1))
            ]
        ])
        status 200
    }
}
