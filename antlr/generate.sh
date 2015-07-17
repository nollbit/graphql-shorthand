#!/bin/bash
java -cp antlr-4.5.1-complete.jar org.antlr.v4.Tool -visitor -package se.mjones.graphql.shorthand.parser -o ../src/main/java/se/mjones/graphql/shorthand/parser GraphQLShorthand.g4
