package com.game.rockgameserver.rest

import org.springframework.cloud.contract.spec.Contract

import static com.game.rockgameserver.util.CustomRegex.customPositiveInt

Contract.make {
    description "should return the list of games"
    request{
        method GET()
        url "/game/all"
        contentType: "application/json"
    }
    response {
        status 200
        body([
            [
              id: $(p(positiveInt()), c(1)),
              rounds: $(p(positiveInt()), c(3)),
              players: [[
                id: $(p(positiveInt()), c(1)),
                name: $(p(anyNonEmptyString()), c("player1")),
                lastMovement: $(p(anyNonEmptyString()), c("ROCK")),
                wins: $(p(customPositiveInt()), c(1)),
                draws: $(p(customPositiveInt()), c(1))
              ],[
                id: 2,
                name: $(p(anyNonEmptyString()), c("player2")),
                lastMovement: $(p(anyNonEmptyString()), c("ROCK")),
                wins: $(p(customPositiveInt()), c(0)),
                draws: $(p(customPositiveInt()), c(1))
              ]],
              gameStats: [
                  WINS_PLAYER_1: $(p(customPositiveInt()), c(0)),
                  WINS_PLAYER_2: $(p(customPositiveInt()), c(0)),
                  DRAWS: $(p(customPositiveInt()), c(1))
              ]
              ],[
                id: $(p(positiveInt()), c(2)),
                rounds: $(p(positiveInt()), c(2)),
                players: [[
                      id: $(p(positiveInt()), c(1)),
                      name: $(p(anyNonEmptyString()), c("player1")),
                      lastMovement: $(p(anyNonEmptyString()), c("ROCK")),
                      wins: $(p(customPositiveInt()), c(0)),
                      draws: $(p(customPositiveInt()), c(1))
                  ],[
                      id: $(p(positiveInt()), c(2)),
                      name: $(p(anyNonEmptyString()), c("player2")),
                      lastMovement: $(p(anyNonEmptyString()), c("ROCK")),
                      wins: $(p(customPositiveInt()), c(0)),
                      draws: $(p(customPositiveInt()), c(1))
                  ]],
                gameStats: [
                    WINS_PLAYER_1: $(p(customPositiveInt()), c(0)),
                    WINS_PLAYER_2: $(p(customPositiveInt()), c(0)),
                    DRAWS: $(p(customPositiveInt()), c(1))
                ]
            ]
        ])
    }
}

