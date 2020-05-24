defmodule CodebrothersCustomer.Repo do
  use Ecto.Repo,
    otp_app: :codebrothers_customer,
    adapter: Ecto.Adapters.Postgres
end
