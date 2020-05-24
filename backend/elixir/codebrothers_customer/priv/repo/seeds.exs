# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#
# Inside the script, you can read and write to any of your
# repositories directly:
#
#     CodebrothersCustomer.Repo.insert!(%CodebrothersCustomer.SomeSchema{})
#
# We recommend using the bang functions (`insert!`, `update!`
# and so on) as they will fail if something goes wrong.


alias CodebrothersCustomer.Repo
alias CodebrothersCustomer.Context.Customer

Repo.insert! %Customer{
  ativo: true,
  cpf: "123.345.567-89",
  email: "elixir@teste.com",
  nome: "Teste",
  sobrenome: "Elixir"
}
