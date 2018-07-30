CREATE TABLE public."AGREEMENT"
(
  "ID"                        INTEGER NOT NULL,
  "START"                     DATE,
  "END"                       DATE,
  "CLIENT_ID"                 INTEGER NOT NULL,
  "SELLER_ID"                 INTEGER,
  "SPEC_ID"                   INTEGER,
  "SPEC_PRODUCTS_ID_PRODUCTS" INTEGER,
  CONSTRAINT "AGREEMENT_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."BANK"
(
  "NAME"         "char",
  "ID"           INTEGER NOT NULL,
  "ACCOUNT2"     "char",
  "ACCOUNT1"     "char",
  "MFO"          "char",
  CONSTRAINT "BANK_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."CLIENT"
(
  "ID"       INTEGER NOT NULL,
  "NAME"     "char",
  "EDRPOU"   "char",
  "DIRECTOR" "char",
  CONSTRAINT "CLIENT_PKEY" PRIMARY KEY ("ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public."PRODUCTS"
(
  "ID"         INTEGER NOT NULL,
  "NAME"       "char",
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
  "MESURE"              "char",
  "PRODUCTS_ID"         INTEGER NOT NULL,
  CONSTRAINT "SPECIFICATION_PKEY" PRIMARY KEY ("ID", "PRODUCTS_ID")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."USER"
(
  "ID"         INTEGER NOT NULL,
  "NAME"       "char",
  "PASSWORLD"  "char",
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
  "STREET"    "char",
  "HOME"      "char",
  "APARTMENT" "char",
  "CITY"      "char",
  CONSTRAINT ADDRESS_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."ROLE"
(
  "ID"      INTEGER NOT NULL,
  "TYPE"   "char",
  CONSTRAINT ROLE_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public."SELLER"
(
  "ID"       INTEGER NOT NULL,
  "NAME"     "char",
  "EDRPOU"   "char",
  "DIREKTOR" "char",
  "NDS"      BOOLEAN,
  CONSTRAINT SELLER_PKEY PRIMARY KEY (ID)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;





