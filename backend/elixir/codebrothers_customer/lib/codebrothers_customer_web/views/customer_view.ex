defmodule CodebrothersCustomerWeb.CustomerView do
  use CodebrothersCustomerWeb, :view
  alias CodebrothersCustomerWeb.CustomerView

  def render("index.json", %{tb_customer: tb_customer}) do
    %{data: render_many(tb_customer, CustomerView, "customer.json")}
  end

  def render("show.json", %{customer: customer}) do
    %{data: render_one(customer, CustomerView, "customer.json")}
  end

  def render("customer.json", %{customer: customer}) do
    %{id: customer.id,
      id: customer.id,
      ativo: customer.ativo,
      atualizadoem: customer.atualizadoem,
      cpf: customer.cpf,
      criadoem: customer.criadoem,
      datanascimento: customer.datanascimento,
      email: customer.email,
      nome: customer.nome,
      rg: customer.rg,
      sobrenome: customer.sobrenome}
  end
end
