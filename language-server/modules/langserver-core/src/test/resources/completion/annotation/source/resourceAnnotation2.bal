import ballerina/http;

service serviceName on new http:Listener(8080) {
    @http:ResourceConfig {
        
    }
    @
    resource function newResource(http:Caller caller, http:Request request) {
        
    }
}