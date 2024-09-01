/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Groovy/GroovyScript.groovy to edit this template
 */

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        urlPath('/matches/by-ids') {
            queryParameters {
                parameter 'ids': '1'
                parameter 'ids': '2'
            }            
        }
    }
    response {
        status 200
        body([
            ["id":1,"league":["id":1,"name":"Premier League"],"homeTeam":["id":1,"name":"Everton"],"awayTeam":["id":2,"name":"Liverpool"],"round":"Regualr season - 1","date":"2024-10-15T20:00:00","status":"COMPLETED","homeTeamGoals":0,"awayTeamGoals":0],
            ["id":2,"league":["id":1,"name":"Premier League"],"homeTeam":["id":3,"name":"Arsenal"],"awayTeam":["id":4,"name":"Chelsea"],"round":"Regualr season - 1","date":"2024-10-15T20:00:00","status":"COMPLETED","homeTeamGoals":2,"awayTeamGoals":0]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
