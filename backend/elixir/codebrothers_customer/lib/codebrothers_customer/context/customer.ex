defmodule CodebrothersCustomer.Context.Customer do
  use Ecto.Schema
  import Ecto.Changeset

  schema "tb_customer" do
    field :ativo, :boolean, default: false
    field :atualizadoem, :date
    field :cpf, :string
    field :criadoem, :date
    field :datanascimento, :date
    field :email, :string
    field :nome, :string
    field :rg, :string
    field :sobrenome, :string

    #timestamps()
  end

  @doc false
  def changeset(customer, attrs) do
    customer
    |> cast(attrs, [:id, :ativo, :atualizadoem, :cpf, :criadoem, :datanascimento, :email, :nome, :rg, :sobrenome])
    |> validate_required([:id, :ativo, :atualizadoem, :cpf, :criadoem, :datanascimento, :email, :nome, :rg, :sobrenome])
  end
end
