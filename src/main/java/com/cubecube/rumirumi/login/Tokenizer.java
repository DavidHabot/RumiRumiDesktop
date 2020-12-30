package com.cubecube.rumirumi.login;

import com.cubecube.rumirumi.login.account.Tokenizable;

public abstract class Tokenizer {
    protected final Encryptionable encryption; //토큰화할 암호화 방식 (현재는 sha256 사용예정)

    public Tokenizer(Encryptionable encryption) {
        this.encryption = encryption;
    }

    public <E extends Tokenizable> String tokenize(E target) {
        String data = target.getPreTokenizedData();
        return encryption.encrypt(data);
    }
}
