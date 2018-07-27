CREATE DATABASE "Agreement"
WITH
OWNER = postgres
ENCODING = 'UTF8'
LC_COLLATE = 'Russian_Russia.1251'
LC_CTYPE = 'Russian_Russia.1251'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

CREATE TABLE public."Agreement"
(
  "Id_Agreement"              INTEGER NOT NULL,
  "Start"                    DATE,
  "End"                      DATE,
  "Client_id_Client"          INTEGER NOT NULL,
  Seller_id_seller            INTEGER,
  "Spec_id_Spec"              INTEGER,
  "Spec_Products_id_Products" INTEGER,
  CONSTRAINT "Agreement_pkey" PRIMARY KEY ("Id_Agreement")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Agreement"
  OWNER TO postgres;


CREATE TABLE public."Bank"
(
  "Name_Of_Bank" "char",
  "Id_Bank"     INTEGER NOT NULL,
  Account2     "char",
  Account1     "char",
  "MFO"        "char",
  CONSTRAINT "Bank_pkey" PRIMARY KEY ("Id_Bank")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Bank"
  OWNER TO postgres;

CREATE TABLE public."Client"
(
  "Id_Client" INTEGER NOT NULL,
  "Name"     "char",
  "EDRPOU"   "char",
  "Director" "char",
  CONSTRAINT "Client_pkey" PRIMARY KEY ("Id_Client")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Client"
  OWNER TO postgres;

CREATE TABLE public."Products"
(
  "Id_Products" INTEGER NOT NULL,
  "Name"       "char",
  CONSTRAINT "Products_pkey" PRIMARY KEY ("Id_Products")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Products"
  OWNER TO postgres;


CREATE TABLE public."Spec"
(
  "Id_Spec"              INTEGER NOT NULL,
  "Price"               DOUBLE PRECISION,
  Quantity              DOUBLE PRECISION,
  Mesure                "char",
  "Products_id_Products" INTEGER NOT NULL,
  CONSTRAINT "Spec_pkey" PRIMARY KEY ("Id_Spec", "Products_id_Products")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Spec"
  OWNER TO postgres;

CREATE TABLE public."User"
(
  "Id_User"    INTEGER NOT NULL,
  "Name"      "char",
  Password    "char",
  Role_id_role INTEGER,
  CONSTRAINT "User_pkey" PRIMARY KEY ("Id_User")
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."User"
  OWNER TO postgres;


CREATE TABLE public.adress
(
  Id_adress  INTEGER NOT NULL,
  Street    "char",
  Home      "char",
  Apartment "char",
  City      "char",
  CONSTRAINT adress_pkey PRIMARY KEY (Id_adress)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.adress
  OWNER TO postgres;

CREATE TABLE public.role
(
  Id_role INTEGER NOT NULL,
  Type   "char",
  CONSTRAINT role_pkey PRIMARY KEY (Id_role)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.role
  OWNER TO postgres;

CREATE TABLE public.seller
(
  Id_seller   INTEGER NOT NULL,
  "Name"     "char",
  "EDRPOU"   "char",
  "Director" "char",
  "NDS"      BOOLEAN,
  CONSTRAINT seller_pkey PRIMARY KEY (Id_seller)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.seller
  OWNER TO postgres;



