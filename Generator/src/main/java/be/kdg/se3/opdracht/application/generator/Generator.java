package be.kdg.se3.opdracht.application.generator;

/**
 * Abstraction of a Generator type class
 */
public interface Generator<TYPE> {

    TYPE generate();
}
