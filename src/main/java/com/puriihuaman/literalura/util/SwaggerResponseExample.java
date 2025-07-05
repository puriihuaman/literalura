package com.puriihuaman.literalura.util;

public class SwaggerResponseExample {
    public static final String CREATED_EXAMPLE = """
                                                 {
                                                   "data": {},
                                                   "hasError": false,
                                                   "message": "Created resource.",
                                                   "statusCode": 201,
                                                   "timestamp": "2025-04-13T15:42:00"
                                                 }
                                                 """;
    
    public static final String SIMPLE_EXAMPLE_GET_ALL_RESOURCE = """
                                                                 {
                                                                   "data": [{}, {}],
                                                                   "hasError": false,
                                                                   "message": "Resources successfully recovered.",
                                                                   "statusCode": 200,
                                                                   "timestamp": "2025-04-13T15:42:00"
                                                                 }
                                                                 """;
    
    public static final String EXAMPLE_OF_GETTING_RESOURCES_WITH_PAGINATION = """
                                                                              {
                                                                                "data": [{}, {}],
                                                                                "pagination": {
                                                                                    "pageNumber": 0,
                                                                                    "pageSize": 15,
                                                                                    "totalPages": 10,
                                                                                    "totalElements": 100,
                                                                                    "numberOfElements": 15,
                                                                                    "first": true,
                                                                                    "last": false,
                                                                                    "sorted": true,
                                                                                    "unsorted": false,
                                                                                    "empty": false
                                                                                },
                                                                                "hasError": false,
                                                                                "message": "Resources successfully recovered.",
                                                                                "statusCode": 200,
                                                                                "timestamp": "2025-04-13T15:42:00"
                                                                              }
                                                                              """;
    
    public static final String EXAMPLE_GET_RESOURCE = """
                                                      {
                                                        "data": {},
                                                        "hasError": false,
                                                        "message": "Resource successfully recovered.",
                                                        "statusCode": 200,
                                                        "timestamp": "2025-04-13T15:42:00"
                                                      }
                                                      """;
}