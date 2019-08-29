package contracts

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
              rounds: 1,
              playersNames: [
                      "rocky",
                      "flocky"
              ],
              stats: [1,2,3,4,5]
            ],[
                id: 2,
                rounds: 2,
                playersNames: [
                       "rockyA",
                        "flockyA"
                ],
                stats: [10, 20, 30, 40, 50]
            ],[
                id: 3,
                rounds: 3,
                playersNames: [
                       "rockyB",
                       "flockyB"
                ],
                stats: [100, 200, 300, 400, 500]
            ]])
        bodyMatchers {
            jsonPath('$[*].stats', byType {
                // results in verification of size of array (min 0)
                minOccurrence(1)
            })
            jsonPath('$[*].playersNames', byType {
                // results in verification of size of array (min 0)
                minOccurrence(2)
            })
        }
        status 200
    }
}