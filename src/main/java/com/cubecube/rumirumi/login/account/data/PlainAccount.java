package com.cubecube.rumirumi.login.account.data;

import com.cubecube.rumirumi.login.account.Tokenizable;
import lombok.Data;

@Data
public class PlainAccount extends Account {
    private String id; //해당 계정의 아이디 정보이다.
    private String pw; //해당 계정의 비밀번호 정보이다.
}
