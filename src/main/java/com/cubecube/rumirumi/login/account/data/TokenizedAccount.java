package com.cubecube.rumirumi.login.account.data;

import com.cubecube.rumirumi.login.Tokenizer;
import com.cubecube.rumirumi.login.account.AccountTokenizer;
import com.cubecube.rumirumi.login.account.SHA256Encrypter;
import com.cubecube.rumirumi.login.account.Tokenizable;
import com.thedeanda.lorem.LoremIpsum;
import lombok.Getter;

public class TokenizedAccount extends Account{
    @Getter
    private final String key; //rainbow table 을 활용한 취약점 공격을 방지하기위해 Account 최초생성시 랜덤한 String 값의 key 를 추가한다.
    private final Tokenizer tokenizer;
    @Getter
    private String id; //해당 계정의 id 정보이다.
    @Getter
    private String token; //계정의 비밀번호 및 rainbow table 의 방지용은 key 값을 추가하여 생성된 토큰
    
    public TokenizedAccount(String id, String pw) {
        this(LoremIpsum.getInstance().getWords(1), id, pw); //키값으로, LoremIpsum 생성기에서 가져온 랜덤한 한 단어를 사용한다.
    }

    public TokenizedAccount(String key, PlainAccount account) {
        this(key, account.getId(), account.getPw()); //PlainAccount 에서 아이디와 비밀번호를 가져와 토큰계정으로 변환한다.
    }
    private TokenizedAccount(String key, String id, String pw) {
        this(key, id, pw, new AccountTokenizer(new SHA256Encrypter()));
    }

    private TokenizedAccount(String key, String id, String pw, Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.key = key; //이후 계정 인증을 위해서 키값을저장해둔다
        this.id = id; //해당 계정의 id 정보를 초기화한다.
        String preTokenized = pw + key;
        tokenizer.tokenize(() -> preTokenized);
    }
}
