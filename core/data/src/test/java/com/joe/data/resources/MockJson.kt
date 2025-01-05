package com.joe.data.resources

class MockJson{
    companion object{
        const val ERROR_MESSAGE = "Error Message"

        const val ERROR_JSON = "{\n" +
                "  \"success\": false,\n" +
                "  \"status_code\": 400,\n" +
                "  \"status_message\": \"$ERROR_MESSAGE\"\n" +
                "}"
    }
}