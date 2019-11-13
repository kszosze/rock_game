package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "put a game id in order to play a round"
    request{
        method PUT()
        url $(c(~/\/game\/[0-9]*/), p("/game/1"))
    }
    response {
        body([
            id: $(p(positiveInt()), c(1)),
            rounds: $(p(anyInteger()), c(1)),
            players: [
                    [
                        id: $(p(positiveInt()), c(1)),
                        name: $(p(anyNonEmptyString()),c("playerrock")),
                        lastMovement: $(p("ROCK"), c("ROCK")),
                        wins: $(p(anyInteger()), c(1)),
                        draws: $(p(anyInteger()), c(0))
                    ],
                    [
                            id: $(p(positiveInt()), c(2)),
                            name: $(p("retro"),c("retro")),
                            lastMovement: $(p("SCISSORS"), c("SCISSORS")),
                            wins: $(p(anyInteger()), c(0)),
                            draws: $(p(anyInteger()), c(0))
                    ]
            ],
            gameStats: [
                    WINS_PLAYER_1: $(p(anyInteger()), c(1)),
                    WINS_PLAYER_2: $(p(anyInteger()), c(0)),
                    DRAWS: $(p(anyInteger()), c(0))
            ]
        ])
        status 200
    }
}
