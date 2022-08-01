/*
Relacao de imobiliaria com seus atributos

imobiliaria (nome,[vendedor])

vendedor(nome,[cliente])

cliente(numero,idade,profissao,[imovel])

imovel(numquartos,preco).

/* base de conhecimento imobiliaria alegrete*/

imobiliaria(alegrete,[vendedor(maria,[cliente(1010,55,veterinario,[casa(3,300),apartamento(1,120)]),
                                      cliente(1011,53,medico,[apartamento(1,120)])]),
                      vendedor(rosa,[cliente(1012,40,militar,[apartamento(2,300)])]),
                      vendedor(miguel,[cliente(1013,65,advogado,[apartamento(3,650),
                                                                      apartamento(1,145),
                                                                      apartamento(2,160)])])
                     ]).


/*base de conhecimento imobiliaria baitachao*/

imobiliaria(baitachao,[vendedor(paulo,[cliente(2010,60,advogado,[casa(3,700)]),
                                       cliente(2012,39,veterinario,[casa(2,260),casa(1,150)])]),
                       vendedor(marisa,[cliente(2011,45,militar,[apartamento(2,250)])])
]).

%base de conhecimento imobiliaria ibirapuita
imobiliaria(ibira,[vendedor(ana,[cliente(3010,25,professor,[apartamento(1,130)])]),
                   vendedor(jose,[cliente(3011,35,professor,[casa(1,120)])]),
                   vendedor(lucas,[cliente(3012,47,medico,[casa(2,250),
                                                            apartamento(2,140)])])]).


 /* 2.1 - Listar clientes: devolve uma lista com o
 codigo de todos os clientes da imobiliaria */

listar_clientes(X,LC):-
    imobiliaria(X,L),
    findall(Num,member(vendedor(_,[cliente(Num,_,_,_)]),L),LC).


%devolve a lista com vendedores de uma imobiliaria
listar_vendedor(X,LV):-
imobiliaria(X,L),
    findall(C,member(vendedor(C,_),L),LV).


