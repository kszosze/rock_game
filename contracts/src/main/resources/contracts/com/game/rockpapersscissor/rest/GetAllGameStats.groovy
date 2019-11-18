package contracts.com.game.rockpapersscissor.rest

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return a list of games stats"
    request{
        method GET()
        url("/game/stats")
    }
    response {
        body([[
              id: 1,
              rounds: 3,
              playersNames: [
                  "Tom",
                  "rocky"
              ],
              stats: [1,2,3,4,5]
            ],[
                id: 2,
                rounds: 3,
                playersNames: [
                   "TomA",
                    "rockyA"
                ],
                stats: [10, 20, 30, 40, 50]
            ],[
                id: 3,
                rounds: 3,
                playersNames: [
                   "TomB",
                   "rockyB"
                ],
                stats: [100, 200, 300, 400, 500]
            ]])
        bodyMatchers {
            jsonPath('$[*].stats', byType {
                minOccurrence(0)
            })
            jsonPath('$[*].playersNames', byType {
                minOccurrence(0)
            })
        }
        status 200
    }
}
