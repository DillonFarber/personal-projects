package com.github.dillonfarber.thelistoffavorites

import retrofit2.http.GET
import retrofit2.http.Path


interface IGDBServices{

    @GET("")
}


class GamingAPI {

    private val clientid = "wdcga06wfwk5r6ioi0el9i89sstg6m"
    private val clientsec = "ga6xcepjh9tuucl4kjkwfof9he0bx6"
}
