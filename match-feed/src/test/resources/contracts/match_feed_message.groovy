/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Groovy/GroovyClass.groovy to edit this template
 */
import org.springframework.cloud.contract.spec.Contract

/**
 *
 * @author Predrag
 */
def contractDsl = Contract.make {
    name "eventSupplierContract"
    label 'match_event_message'
    input {
        triggeredBy('eventSupplier()')
    }
    outputMessage {
        sentTo('matchEvent')
        body(
            fixture: [
                fixtureId           : 12345,
                fixtureStatusShort  : "2H",
                fixtureElapsed      : 90
            ],
            league: [
                name    : "Premier League",
                country : "England",
                round   : "Regular season - 1"
            ],
            teams: [
                home    : "Manchester United",
                away    : "Chelsea"
            ],
            goals: [
                home    : 2,
                away    : 1
            ],
            info: [
                time        : 88,
                teamName    : "Manchester United",
                playerName  : "Rashford",
                type        : "Goal"
            ]
        )
        headers {
            messagingContentType(applicationJson())
        }
    }
}

