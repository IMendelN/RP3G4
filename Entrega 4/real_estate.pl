:- dynamic list_set/2.

% ------------------------------------------------------------------------------------------------
% Profissões -> Profissão.
% ------------------------------------------------------------------------------------------------
career('Veterinário').
career('Médico').
career('Militar').
career('Advogado').
career('Professor').

% ------------------------------------------------------------------------------------------------
% Clientes -> Código, Idade, Profissão.
% ------------------------------------------------------------------------------------------------
client(1010, 55, career('Veterinário')).
client(1011, 53, career('Médico')).
client(1012, 40, career('Militar')).
client(1013, 65, career('Advogado')).
client(2010, 60, career('Advogado')).
client(2011, 45, career('Militar')).
client(2012, 39, career('Veterinário')).
client(3010, 25, career('Professor')).
client(3011, 35, career('Professor')).
client(3012, 47, career('Médico')).

% ------------------------------------------------------------------------------------------------
% Vendedores -> Nome.
% ------------------------------------------------------------------------------------------------
seller('Maria').
seller('Paulo').
seller('Ana').
seller('José').
seller('Lucas').
seller('Marisa').
seller('Rosa').
seller('Miguel').

% ------------------------------------------------------------------------------------------------
% Imobiliárias -> Imobiliária.
% ------------------------------------------------------------------------------------------------
agency_in('Alegrete').
agency_in('Baita Chão').
agency_in('Ibirapuitã').

% ------------------------------------------------------------------------------------------------
% Tipos de Imóveis -> Tipo.
% ------------------------------------------------------------------------------------------------
type('Casa').
type('Apartamento').

% ------------------------------------------------------------------------------------------------
% Imóveis para locação -> Tipo, Quartos, Valor.
% ------------------------------------------------------------------------------------------------
for_sale(type('Casa'), 1, 150_000).
for_sale(type('Casa'), 1, 220_000).
for_sale(type('Casa'), 2, 250_000).
for_sale(type('Casa'), 2, 260_000).
for_sale(type('Casa'), 3, 300_000).
for_sale(type('Casa'), 3, 700_000).
for_sale(type('Apartamento'), 1, 120_000).
for_sale(type('Apartamento'), 1, 130_000).
for_sale(type('Apartamento'), 2, 140_000).
for_sale(type('Apartamento'), 2, 250_000).
for_sale(type('Apartamento'), 2, 300_000).
for_sale(type('Apartamento'), 3, 500_000).

% ------------------------------------------------------------------------------------------------
% Vendas -> Imobiliária, Vendedor, Cliente, Imóvel.
% ------------------------------------------------------------------------------------------------
sale(agency_in('Alegrete'), seller('Maria'), client(1010, 55, career('Veterinário')), for_sale(type('Casa'), 3, 300_000)).
sale(agency_in('Alegrete'), seller('Maria'), client(1010, 55, career('Veterinário')), for_sale(type('Apartamento'), 1, 120_000)).
sale(agency_in('Alegrete'), seller('Miguel'), client(1013, 65, career('Advogado')), for_sale(type('Apartamento'), 3, 650_000)).
sale(agency_in('Alegrete'), seller('Miguel'), client(1013, 65, career('Advogado')), for_sale(type('Apartamento'), 1, 145_000)).
sale(agency_in('Alegrete'), seller('Miguel'), client(1013, 65, career('Advogado')), for_sale(type('Apartamento'), 2, 260_000)).
sale(agency_in('Alegrete'), seller('Rosa'), client(1012, 40, career('Militar')), for_sale(type('Apartamento'), 2, 300_000)).
sale(agency_in('Alegrete'), seller('Maria'), client(1011, 53, career('Médico')), for_sale(type('Apartamento'), 3, 500_000)).
sale(agency_in('Ibirapuitã'), seller('Ana'), client(3010, 25, career('Professor')), for_sale(type('Apartamento'), 1, 130_000)).
sale(agency_in('Ibirapuitã'), seller('José'), client(3011, 35, career('Professor')), for_sale(type('Casa'), 1, 220_000)).
sale(agency_in('Ibirapuitã'), seller('Lucas'), client(3012, 47, career('Médico')), for_sale(type('Casa'), 2, 250_000)).
sale(agency_in('Ibirapuitã'), seller('Lucas'), client(3012, 47, career('Médico')), for_sale(type('Apartamento'), 2, 140_000)).
sale(agency_in('Baita Chão'), seller('Paulo'), client(2010, 60, career('Advogado')), for_sale(type('Casa'), 3, 700_000)).
sale(agency_in('Baita Chão'), seller('Marisa'), client(2011, 45, career('Militar')), for_sale(type('Apartamento'), 2, 250_000)).
sale(agency_in('Baita Chão'), seller('Paulo'), client(2012, 39, career('Veterinário')), for_sale(type('Casa'), 2, 260_000)).
sale(agency_in('Baita Chão'), seller('Paulo'), client(2012, 39, career('Veterinário')), for_sale(type('Casa'), 1, 150_000)).

% ------------------------------------------------------------------------------------------------
% REGRAS
% ------------------------------------------------------------------------------------------------
% 2.1: Listar o código de cada cliente da imobiliária.
% ------------------------------------------------------------------------------------------------
list_clients :-
    nl, write('Lista de código de clientes: '),
    findall(Code, client(Code, _, _), Codes),
    writeln(Codes).

% ------------------------------------------------------------------------------------------------
% 2.2: Listar os dados de um determinado cliente.
% ------------------------------------------------------------------------------------------------
list_client(Code) :-
    client(Code, Age, career(Career)),
    writef('Código: %d\nIdade: %d\nProfissão: %w\n', [Code, Age, Career]).

% ------------------------------------------------------------------------------------------------
% 2.3: Listar todos os tipos de imóveis vendidos por uma determinada imobiliária.
% ------------------------------------------------------------------------------------------------
list_types_by_agency(Agency) :-
    findall(Type, sale(agency_in(Agency), _, _, for_sale(type(Type), _, _)), NonUniqueTypes),
    list_to_set(NonUniqueTypes, Types),
    writef('Imóveis vendidos pela imobilária de %w: %w\n', [Agency, Types]).

% ------------------------------------------------------------------------------------------------
% 2.4: Listar todos os clientes de uma determinada profissão.
% ------------------------------------------------------------------------------------------------
list_client_by_career(Career) :-
    findall(Code, client(Code, _, career(Career)), Codes),
    writef('Clientes da profissão \'%w\': %w\n', [Career, Codes]).

% ------------------------------------------------------------------------------------------------
% 2.5: (Bônus) - Valor médio de imóveis vendidos por uma determinada imobiliária.
% ------------------------------------------------------------------------------------------------
average_value_by_agency(Agency) :-
    findall(Value, sale(agency_in(Agency), _, _, for_sale(_, _, Value)), Values),
    sum_list(Values, Sum),
    length(Values, Length),
    Average is (Sum div Length),
    writef('Valor médio de imóveis vendidos pela imobilária \'%w\' é de: ', [Agency]),
    format('R$ ~2f\n', [Average]).

% ------------------------------------------------------------------------------------------------
% 2.5: Listagem do valor médio de todas as imobiliárias.
% ------------------------------------------------------------------------------------------------
list_average_agencies :-
    forall(agency_in(Agency), average_value_by_agency(Agency)).

% ------------------------------------------------------------------------------------------------
% 2.6: Alterar informação de um determinado cliente.
% ------------------------------------------------------------------------------------------------
% [WORKING...]
% ------------------------------------------------------------------------------------------------
change_client(Code, NewAge, NewCareer) :-
    client(Code, _, _),
    retract(client(Code, _, _)),
    assert(client(Code, NewAge, career(NewCareer))).

% ------------------------------------------------------------------------------------------------
% 2.7: Listar imobiliárias com maior valor de vendas em ordem decrescente.
% ------------------------------------------------------------------------------------------------
% [WORKING...]
% ------------------------------------------------------------------------------------------------
list_agency_by_value :-
    findall(Agency, sale(agency_in(Agency), _, _, for_sale(_, _, _)), Agencies),
    sort(Agencies, SortedAgencies),
    forall(member(Agency, SortedAgencies), (
        findall(Value, sale(agency_in(Agency), _, _, for_sale(_, _, Value)), Values),
        sum_list(Values, Sum),
        format('Imobiliária de \'~w\' possui o valor de R$ ~2f em vendas.\n', [Agency, Sum])
    )),
    writef('\nImobiliárias com maior valor de vendas: %w\n', [SortedAgencies]).

% ------------------------------------------------------------------------------------------------
% 2.8: Listar vendedores em ordem crescente de valor de vendas.
% ------------------------------------------------------------------------------------------------
% [WORKING...]
% ------------------------------------------------------------------------------------------------
list_sellers :-
    findall((Seller, Value), sale(_, Seller, _, for_sale(_, _, Value)), Sellers),
    write(Sellers).
