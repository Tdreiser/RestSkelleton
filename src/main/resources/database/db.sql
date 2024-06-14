
create TABLE IF NOT EXISTS public.cars (
    id integer NOT NULL,
    mark character varying(255),
    model character varying(255),
    manufactureyear integer
);

alter table public.cars OWNER TO postgres;

create sequence  IF NOT EXISTS public.cars_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

alter table public.cars_id_seq OWNER TO postgres;

alter sequence public.cars_id_seq OWNED BY public.cars.id;

create TABLE  IF NOT EXISTS  public.clients (
    id integer NOT NULL,
    email character varying(255),
    name character varying(255),
    phone character varying(255),
    car_id integer
);

create TABLE  IF NOT EXISTS  public.messages (
    id integer NOT NULL,
    message TEXT
);

create sequence  IF NOT EXISTS public.messages_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

alter table public.messages_id_seq OWNER TO postgres;
alter table ONLY public.messages drop constraint if exists messages_pkey cascade;
alter table ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);

alter table public.cars_id_seq OWNER TO postgres;

alter table public.clients OWNER TO postgres;

create sequence  IF NOT EXISTS  public.clients_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table if not exists public.clients_messages(
    client_id integer,
    message_id integer
);
alter table public.clients_id_seq OWNER TO postgres;

alter sequence public.clients_id_seq OWNED BY public.clients.id;

alter table ONLY public.cars alter COLUMN id SET DEFAULT nextval('public.cars_id_seq'::regclass);

alter table ONLY public.messages alter COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);

alter table ONLY public.clients alter COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);

alter table ONLY public.cars drop constraint if exists cars_pkey cascade;
alter table ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);

alter table ONLY public.clients drop constraint if exists clients_pkey cascade;
alter table ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);

alter table ONLY public.clients drop constraint if exists fk7ncdpoo5m67gbghrq4o1qaaul cascade;
alter table ONLY public.clients
    ADD CONSTRAINT fk7ncdpoo5m67gbghrq4o1qaaul FOREIGN KEY (car_id) REFERENCES public.cars(id);


