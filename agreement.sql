CREATE TABLE public."AGREEMENT"
(
  "ID"                        INTEGER NOT NULL,
  "START"                     DATE,
  "END"                       DATE,
  "SPECIFICATION_ID"          INTEGER NOT NULL,
  "NAMBER"                    varchar(50),
  CONSTRAINT "AGREEMENT_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."BANK"
(
  "NAME"         varchar(50),
  "ID"           INTEGER NOT NULL,
  "ACCOUNT2"     varchar(15),
  "ACCOUNT1"     varchar(15),
  "MFO"          varchar(6),
  CONSTRAINT "BANK_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."CLIENT"
(
  "ID"       INTEGER NOT NULL,
  "NAME"      varchar(50),
  "EDRPOU"    varchar(15),
  "DIRECTOR"  varchar(50),
  CONSTRAINT "CLIENT_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."PRODUCTS"
(
  "ID"         INTEGER NOT NULL,
  "NAME"       varchar(50),
  CONSTRAINT "PRODUCTS_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."SPECIFICATION"
(
  "ID"                  INTEGER NOT NULL,
  "PRICE"               DOUBLE PRECISION,
  "QUANTITY"            DOUBLE PRECISION,
  "MESURE"              varchar(8),
  "PRODUCTS_ID"         INTEGER NOT NULL,
  CONSTRAINT "SPECIFICATION_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."USER"
(
  "ID"         INTEGER NOT NULL,
  "NAME"       varchar(50),
  "PASSWORLD"  varchar(50),
  "ROLE_ID"     INTEGER,
  CONSTRAINT "USER_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."ADDRESS"
(
  "ID"       INTEGER NOT NULL,
  "STREET"    varchar(50),
  "HOME"      varchar(50),
  "APARTMENT" varchar(50),
  "CITY"      varchar(50),
  CONSTRAINT ADDRESS_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."ROLE"
(
  "ID"      INTEGER NOT NULL,
  "TYPE"   varchar(25),
  CONSTRAINT ROLE_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."SELLER"
(
  "ID"       INTEGER NOT NULL,
  "NAME"     varchar(50),
  "EDRPOU"   varchar(15),
  "DIREKTOR" varchar(50),
  "NDS"      BOOLEAN,
  CONSTRAINT SELLER_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)--------------------------------------------------------------------------------------------------------------------------------------------------
TABLESPACE pg_default;

CREATE TABLE public."SELLER_HAS_AGREEMENT"
(
  "SELLER_ID"                    INTEGER NOT NULL,
  "AGREEMENT_ID"                 INTEGER NOT NULL,
  "AGREEMENT_SPECIFICATION_ID"   INTEGER NOT NULL,

  CONSTRAINT SELLER_HAS_AGREEMENT UNIQUE ("SELLER_ID", "AGREEMENT_ID", "AGREEMENT_SPECIFICATION_ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."AGREEMENT_HAS_CLIENT"
(
  "CLIENT_ID"                    INTEGER NOT NULL,
  "AGREEMENT_ID"                 INTEGER NOT NULL,
  "AGREEMENT_SPECIFICATION_ID"   INTEGER NOT NULL,

  CONSTRAINT AGREEMENT_HAS_CLIENT UNIQUE ("CLIENT_ID", "AGREEMENT_ID", "AGREEMENT_SPECIFICATION_ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


