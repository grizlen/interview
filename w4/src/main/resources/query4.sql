число посетителей и кассовые сборы,
сгруппированные по времени начала фильма:
с 9 до 15,
с 15 до 18,
с 18 до 21,
с 21 до 00:00

select count(*), sum(prices.amount) from sessions
inner join tickets on sessions.id = tickets.session_id
left join prices on sessions.price_id = prices.id
where time(sessions.start, )

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
