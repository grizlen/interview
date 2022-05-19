select * from (
    select
        films.name,
        sum(num_tickets) as all_tickets,
        avg(num_tickets) as avg_tickets,
        sum(cost) as all_cost
    from films
    left join (
        SELECT film_id, sessions.id as session, count(tickets.id) as num_tickets, sum(prices.amount) as cost
        from sessions
        inner join tickets on sessions.id = tickets.session_id
        left join prices on sessions.price_id = prices.id
        group by sessions.id
    ) as A on films.id = a.film_id
    group by films.id
    order by all_cost desc)
union
    select 'total:', count(*), null, sum(prices.amount) from sessions
    inner join tickets on sessions.id = tickets.session_id
    left join prices on sessions.price_id = prices.id;
