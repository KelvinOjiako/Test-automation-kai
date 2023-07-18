package org.kotopia

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.backendSPA(){
    routing {
        singlePageApplication {
            useResources = true
            filesPath = "client-side"
            defaultPage = "index.html"
        }

        get("test"){
            call.respondText("test route works!! Server is currently on")
        }
    }
}

fun main(){
    embeddedServer(Netty, port = 8045) { backendSPA() }.start(wait = true)
}