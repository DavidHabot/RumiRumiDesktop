package com.cubecube.rumirumi.login;

public abstract class Authorizer<E> {
    public abstract void authorize(E e);
}
