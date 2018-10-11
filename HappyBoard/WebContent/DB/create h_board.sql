CREATE TABLE H_BOARD(
    BID NUMBER,
    BTYPE number,
    seq number,
    btitle nvarchar2(100) not null enable,
    bcontent nclob,
    bregdate date default sysdate,
    pw nvarchar2(10),
    primary key (bid)
);

COMMIT;
