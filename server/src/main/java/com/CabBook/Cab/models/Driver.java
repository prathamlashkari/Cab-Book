package com.CabBook.Cab.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "drivers")
public class Driver {

}
