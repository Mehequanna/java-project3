--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: Stephen
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    details character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO "Stephen";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: Stephen
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "Stephen";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Stephen
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: Stephen
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying,
    details character varying
);


ALTER TABLE stylists OWNER TO "Stephen";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: Stephen
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "Stephen";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Stephen
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Stephen
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Stephen
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: Stephen
--

COPY clients (id, name, details, stylist_id) FROM stdin;
1	Default Client	Default Details	1
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Stephen
--

SELECT pg_catalog.setval('clients_id_seq', 4, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: Stephen
--

COPY stylists (id, name, details) FROM stdin;
1	Default Stylist	Default Details
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Stephen
--

SELECT pg_catalog.setval('stylists_id_seq', 5, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: Stephen
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: Stephen
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Stephen
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Stephen";
GRANT ALL ON SCHEMA public TO "Stephen";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

