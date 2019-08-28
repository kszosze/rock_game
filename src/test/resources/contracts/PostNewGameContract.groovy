package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "post a name to create a new game for a new player"
    request{
        method POST()
        url("/game/new/retro")
    }
    response {
        body([
                id: $(p(positiveInt()), c(1)),
                rounds: $(p(anyInteger()), c(0)),
                players: [
                        [
                            id: $(p(positiveInt()), c(1)),
                            name: $(p(anyNonEmptyString()),c("playerrock")),
                            lastMovement: $(p(null), c(null)),
                            wins: $(p(anyInteger()), c(0)),
                            draws: $(p(anyInteger()), c(0)),
                            rounds: $(p(anyInteger()), c(0))
                        ],
                        [
                                id: $(p(positiveInt()), c(2)),
                                name: $(p("retro"),c("retro")),
                                lastMovement: $(p(null), c(null)),
                                wins: $(p(anyInteger()), c(0)),
                                draws: $(p(anyInteger()), c(0)),
                                rounds: $(p(anyInteger()), c(0))
                        ]
                ],
                gameStats: [
                        WINS_PLAYER_1: $(p(anyInteger()), c(0)),
                        WINS_PLAYER_2: $(p(anyInteger()), c(0)),
                        DRAWS: $(p(anyInteger()), c(0))
                ]
        ])
        status 200
    }
}