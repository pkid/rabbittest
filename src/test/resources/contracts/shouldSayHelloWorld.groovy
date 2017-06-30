org.springframework.cloud.contract.spec.Contract.make {
    request { 
        method 'GET' 
        url '/greeting' 
        //body([ 
        //       "client.id": $(regex('[0-9]{10}')),
        //       loanAmount: 99999
        //])
        headers { 
            contentType('application/json')
        }
    }
    response { 
        status 200 
        body([ 
               "content": "Hello, World!"
        ])
        headers { 
            contentType('application/json;charset=UTF-8')
        }
    }
}
