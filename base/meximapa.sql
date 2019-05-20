--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.16
-- Dumped by pg_dump version 11.2 (Ubuntu 11.2-1.pgdg16.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comentario (
    idcomentario integer NOT NULL,
    idmarcador integer NOT NULL,
    idusuario integer NOT NULL,
    comentario character varying(255),
    calificacion integer,
    fecha date DEFAULT ('now'::text)::timestamp(2) with time zone NOT NULL
);


ALTER TABLE public.comentario OWNER TO postgres;

--
-- Name: comentario_idcomentario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comentario_idcomentario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_idcomentario_seq OWNER TO postgres;

--
-- Name: comentario_idcomentario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comentario_idcomentario_seq OWNED BY public.comentario.idcomentario;


--
-- Name: comentario_idmarcador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comentario_idmarcador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_idmarcador_seq OWNER TO postgres;

--
-- Name: comentario_idmarcador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comentario_idmarcador_seq OWNED BY public.comentario.idmarcador;


--
-- Name: comentario_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comentario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_idusuario_seq OWNER TO postgres;

--
-- Name: comentario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comentario_idusuario_seq OWNED BY public.comentario.idusuario;


--
-- Name: marcador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marcador (
    idmarcador integer NOT NULL,
    nombre character varying(255) NOT NULL,
    idusuario integer NOT NULL,
    latitud double precision,
    longitud double precision,
    descripcion character varying(255),
    datos character varying(255)
);


ALTER TABLE public.marcador OWNER TO postgres;

--
-- Name: marcador_idmarcador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marcador_idmarcador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marcador_idmarcador_seq OWNER TO postgres;

--
-- Name: marcador_idmarcador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marcador_idmarcador_seq OWNED BY public.marcador.idmarcador;


--
-- Name: marcador_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marcador_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marcador_idusuario_seq OWNER TO postgres;

--
-- Name: marcador_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marcador_idusuario_seq OWNED BY public.marcador.idusuario;


--
-- Name: tema; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tema (
    nombre character varying(255) NOT NULL,
    idusuario integer NOT NULL,
    color character varying(255)
);


ALTER TABLE public.tema OWNER TO postgres;

--
-- Name: tema_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tema_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tema_idusuario_seq OWNER TO postgres;

--
-- Name: tema_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tema_idusuario_seq OWNED BY public.tema.idusuario;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    idusuario integer NOT NULL,
    nombre character varying(255) NOT NULL,
    apaterno character varying(255),
    amaterno character varying(255),
    contrasenia character varying(255) NOT NULL,
    correo character varying(255) NOT NULL,
    rol integer
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_idusuario_seq OWNER TO postgres;

--
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_idusuario_seq OWNED BY public.usuario.idusuario;


--
-- Name: comentario idcomentario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario ALTER COLUMN idcomentario SET DEFAULT nextval('public.comentario_idcomentario_seq'::regclass);


--
-- Name: comentario idmarcador; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario ALTER COLUMN idmarcador SET DEFAULT nextval('public.comentario_idmarcador_seq'::regclass);


--
-- Name: comentario idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario ALTER COLUMN idusuario SET DEFAULT nextval('public.comentario_idusuario_seq'::regclass);


--
-- Name: marcador idmarcador; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcador ALTER COLUMN idmarcador SET DEFAULT nextval('public.marcador_idmarcador_seq'::regclass);


--
-- Name: marcador idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcador ALTER COLUMN idusuario SET DEFAULT nextval('public.marcador_idusuario_seq'::regclass);


--
-- Name: tema idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tema ALTER COLUMN idusuario SET DEFAULT nextval('public.tema_idusuario_seq'::regclass);


--
-- Name: usuario idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN idusuario SET DEFAULT nextval('public.usuario_idusuario_seq'::regclass);


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comentario (idcomentario, idmarcador, idusuario, comentario, calificacion, fecha) FROM stdin;
1	1	3	los mejores de la zona	5	2019-04-17
\.


--
-- Data for Name: marcador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marcador (idmarcador, nombre, idusuario, latitud, longitud, descripcion, datos) FROM stdin;
1	Chilaquiles	3	19.3365580000000001	-99.1660729999999973	Los mejor del rumbo	avenida siempre viva
2	Batallas de Rap	0	16.590876999999999	-96.4719050000000067	Batallas Gratis	Ven a demostrar y practicar tus habilidades, sin costo, todos los sabados de 8 AM a 2 PM
3	Batallas de Rap	0	18.5463239999999985	-96.0642320000000041	Torneos mensuales	Torneos con proyeccion internacional, primer fin de semana de mes, de 8 de la mañana a 8 de la tarde
4	Batallas de Rap	0	19.9682020000000016	-97.8531839999999988	Los mejores raperos de la zona	Ven a las batallas con la mejor banda del barrio, todos los viernes a las 6pm
5	Batallas de Rap	0	18.2111739999999998	-98.1040729999999996	Freestyle zone	Duelos, free, 4x4, animate a convivir y entrenar tus habilidades, todos los dias hay banda practicando, caele a la tarima
6	Batallas de Rap	0	19.2978449999999988	-99.1639940000000024	Batallas de dioses	Vente a ver a los mejores exponentes de la zona rifarse un tiro como los grandes, no cover, entrada +18
7	Batallas de Rap	0	21.8971440000000008	-102.239851000000002	Batlla de Gallos	Inscribete y entrena para la Batallas de gallos, diario por la tarde aqui te esperamos
8	Batallas de Rap	0	20.4089540000000014	-101.365091000000007	Peleas en la tarima	Pasa un rato de rimas con la bandera del barrio
9	Batallas de Rap	0	22.1763009999999987	-99.7014640000000014	Aprende de los grandes	Clases de Free, todos los sabados a partir de medio dia
10	Batallas de Rap	0	24.5000177999999984	-98.9986399999999946	Saca el microfono	Aqui la fiesta nunca termina, grupo dedicado el frestyle underground, trae a tus amigos a las retas, todos los dias
11	Batallas de Rap	0	26.2229370000000017	-99.0427880000000016	Frontera Peligrosa	Ven a ver al talento fronterizo, todos los viernes a las 8PM
12	Intercambio de Libros	0	26.6574770000000001	-106.750551999999999	Intercambio en Hermosillo	Trae libros que hayas leido, y compartelos con alguien que lo quiera leer, los sabados de 8 a 20
13	Intercambio de Libros	0	30.9201049999999995	-110.732848000000004	Intercambios de libros usados	Todos los fines de semana te esperamos
14	Intercambio de Libros	0	30.9201049999999995	-96.4719050000000067	Cambios Gratis	Ven a Cambair tus libros
15	Intercambio de Libros	0	26.7545220000000015	-106.679839999999999	Cambios de Libros	Comparte el conocimento con todos los asistentes, encuentros masivos, todos los fines de semana de 12 pm a 6pm
16	Intercambio de Libros	0	23.0066419999999994	-100.874870999999999	Trae tus libros	Cada sabado fin de mes te esperamos con tu coleccion de libros para cambiar, todos entran
17	Intercambio de Libros	0	20.7619989999999994	-101.285944999999998	Intercambios de libros cientificos	Solo cambios, libros especializados en ramas de ciencias, todas las tardes en irapuato 
18	Intercambio de Libros	0	22.7418219999999991	-98.039833999999999	Libros de cocina	El club gourmet de aldama tamaulipas te invita todos los jueves a intercambiar libros de cocina por despensa la cual se donara a familias de zonas marginadas
19	Intercambio de Libros	0	19.9398289999999996	-88.9721219999999988	Libros al ataque	Intercambios de libros en una zona natural, convivencia al aire libre
20	Intercambio de Libros	0	21.4597909999999992	-87.0703460000000007	Libros en ingles	Apoya este choque culturar trayendo libros en ingles, se cambian por libros de interes cultura, en cualquier idioma, diario de 8am a 8 pm
21	Intercambio de Libros	0	16.0588240000000013	-93.0165850000000063	Chiapas a la cultura	Intercambios de novelas clasicas, todos los viernes fines de mes por la tarde, te esperamos
22	Puestos Callejeros	0	18.0556449999999984	-94.4263899999999978	Tacos Paisa	De lo mejor para gustos refinados, pase a conocerlos
23	Puestos Callejeros	0	19.3407710000000002	-104.872921000000005	Comida corrida lucy	Un entrañable lugar que no puede dejar de visitar en su estadia por la huerta
24	Puestos Callejeros	0	21.190448	-100.363746000000006	Tacos de la mora	Puesto que logra enriquecer tus papilas gustativas con la primera mordidad
25	Puestos Callejeros	0	25.6086889999999983	-100.311018000000004	Asada de Nuevo Leon	Las mejores carnes asadas de la zona, visitenos diario a partir de las 5 pm
26	Puestos Callejeros	0	25.5282720000000012	-103.227407999999997	Comida Corrida de Matamoros	Lleve su comida para llevar, la mejor de matamoros
27	Puestos Callejeros	0	19.6962599999999988	-103.576267000000001	Birria el sobrino	Zapotlan ofrece la mejor birria de la zona, no deje de visitarnos, crreamos los miercoles
28	Puestos Callejeros	0	20.3149510000000006	-101.138064	Tortas el caleyo	De venta todos los dias por la tarde
29	Puestos Callejeros	0	24.6043149999999997	-102.738319000000004	Matehuala season food	Comida de temporada en matehuala
30	Puestos Callejeros	0	25.1502410000000012	-99.8304729999999978	Tortas Tacos monte monte	La garnacha por excelencia en montemorelos
31	Puestos Callejeros	0	16.8498769999999993	-99.8956859999999978	Tacos la bahia	Los mejores tacos nocturnos con vista a puerto marques
32	Los mejores tacos del rumbo	6	19.3222370000000012	-99.1638130000000046	Los mejor del rumbo	Buenos tacos buena actitud un buen lugar para pasar el tiempo
33	El acuario en CDMX	6	19.4399289999999993	-99.2051869999999951	Un bonito lugar	un bonito lugar para coner la variedad de peces en el mar
34	Un Parque Estilo Japones	6	19.3527790000000017	-99.1423379999999952	Un lugar con tu pareja	bonito lugar estilo japones para tomarte fotos con tu pareja o para presumir entre tus conocidos
35	Un nuevo frape	6	19.367246999999999	-99.166324000000003	Un buen rato para pasar juntos	Buenisimos frapes de diferentes sabores "estilo oriental" para disfrutar solo ó acompañado
\.


--
-- Data for Name: tema; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tema (nombre, idusuario, color) FROM stdin;
Chilaquiles	3	#041D56
Batallas de Rap	0	#1A3AE2
Intercambio de Libros	0	#F7F917
Puestos Callejeros	0	#F99517
Intercambios de estampas	0	#17F9F4
Venta de Juguetes Coleccionables	0	#C917F9
Vulcanizadoras	0	#5F3D02
Los mejores tacos del rumbo	6	#1A5AE2
El acuario en CDMX	6	#F7F913
Un Parque Estilo Japones	6	#F34517
Un nuevo frape	6	#57F9F6
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (idusuario, nombre, apaterno, amaterno, contrasenia, correo, rol) FROM stdin;
1	admin	ad	add	qwerty	Algo@algo.com	1
2	comen	ad	add	qwerty	Algo@alg.com	2
3	infor	ad	add	qwerty	Algo@al.com	3
0	Jesus	Barajas	Figueroa	qwerty	brainb29@gmail.com	3
4	Liz	Arguello	L	qwerty	no_reply@algo.com	3
5	Alexia	M	R	qwerty	alex@gmail.com	3
6	Jorge	Argenis	Hernandez	qwerty	jorge@gmail.com	3
\.


--
-- Name: comentario_idcomentario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comentario_idcomentario_seq', 1, false);


--
-- Name: comentario_idmarcador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comentario_idmarcador_seq', 1, false);


--
-- Name: comentario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comentario_idusuario_seq', 1, false);


--
-- Name: marcador_idmarcador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marcador_idmarcador_seq', 1, false);


--
-- Name: marcador_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marcador_idusuario_seq', 1, false);


--
-- Name: tema_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tema_idusuario_seq', 1, false);


--
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_idusuario_seq', 1, false);


--
-- Name: comentario comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (idcomentario);


--
-- Name: marcador marcador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcador
    ADD CONSTRAINT marcador_pkey PRIMARY KEY (idmarcador);


--
-- Name: tema tema_color_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_color_key UNIQUE (color);


--
-- Name: tema tema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_pkey PRIMARY KEY (nombre);


--
-- Name: usuario usuario_correo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_correo_key UNIQUE (correo);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);


--
-- Name: comentario comentario_idmarcador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_idmarcador_fkey FOREIGN KEY (idmarcador) REFERENCES public.marcador(idmarcador);


--
-- Name: comentario comentario_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.usuario(idusuario);


--
-- Name: marcador marcador_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcador
    ADD CONSTRAINT marcador_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.usuario(idusuario);


--
-- Name: marcador marcador_nombre_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcador
    ADD CONSTRAINT marcador_nombre_fkey FOREIGN KEY (nombre) REFERENCES public.tema(nombre);


--
-- Name: tema tema_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.usuario(idusuario);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

