--
-- PostgreSQL database cluster dump
--
CREATE DATABASE DevsuBD WITH OWNER = postgres ENCODING = 'UTF8';
\c DevsuBD


SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:80Q6bexx7kJN4ltFnWNo1A==$aRjs3UnOZ9VW+5oYhTW11COVX1Hj5/DTJW0X1EI180w=:aHwpXP4JfPNBSOSjEVbvq8RBK+dgBnPHnKth0TLcXbQ=';

--
-- User Configurations
--
--
-- PostgreSQL database cluster dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    account_number bigint NOT NULL,
    account_type character varying(255) NOT NULL,
    initial_balance double precision NOT NULL,
    status boolean NOT NULL,
    customer_id bigint
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    address character varying(255) NOT NULL,
    age integer NOT NULL,
    gender character varying(255) NOT NULL,
    identification character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_seq OWNER TO postgres;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    transaction_id bigint NOT NULL,
    balance double precision NOT NULL,
    date timestamp with time zone NOT NULL,
    initial_balance double precision NOT NULL,
    transaction_type character varying(255) NOT NULL,
    value double precision NOT NULL,
    account_number bigint
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_seq OWNER TO postgres;

--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_number);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id);


--
-- Name: customer uk_rhpv87q3057rohwm2cawtfr3e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_rhpv87q3057rohwm2cawtfr3e UNIQUE (identification);


--
-- Name: transaction fka80kblc0ww9p9xjei8luheqlk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fka80kblc0ww9p9xjei8luheqlk FOREIGN KEY (account_number) REFERENCES public.account(account_number);


--
-- Name: account fknnwpo0lfq4xai1rs6887sx02k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fknnwpo0lfq4xai1rs6887sx02k FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- PostgreSQL database dump complete
--

