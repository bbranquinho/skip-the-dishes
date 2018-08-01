package br.com.skip.the.dishes.query.event.handler.commons


import akka.actor.ActorSystem
import akka.util.Timeout
import eventstore.InvalidOperationException
import eventstore.ProjectionsClient
import eventstore.Settings
import eventstore.j.EsConnection
import eventstore.j.PersistentSubscriptionSettingsBuilder
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Component
class AggregateSubscriber(private val eventStoreApiService: EventStoreApiService) {

    private val logger = LogManager.getLogger(this.javaClass)

    private companion object {
        const val TIMEOUT_CREATE_PERSISTENT_GROUP = 60L
    }

    private val timeout = Timeout(Duration.create(TIMEOUT_CREATE_PERSISTENT_GROUP, "seconds"))

    fun start(aggregateRootName: String, actorSystem: ActorSystem, esConnection: EsConnection) {
        val index = "fromCategory('$aggregateRootName').foreachStream().when({\$any : function" +
                "(s,e) { linkTo(\"$aggregateRootName\", e); }})"

        enableSystemProjections()
        createIndex(projectionName = aggregateRootName, index = index, actorSystem = actorSystem)
        createPersistentGroup(aggregateRootName = aggregateRootName, esConnection = esConnection)
    }

    private fun enableSystemProjections() {
        eventStoreApiService.enableProjection("\$by_category")
    }

    private fun createIndex(projectionName: String, index: String, actorSystem: ActorSystem) {
        ProjectionsClient(Settings.Default(), actorSystem)
                .createProjection(
                        projectionName,
                        index,
                        ProjectionsClient.`ProjectionMode$`.`MODULE$`.apply("Continuous"),
                        true
                )
    }

    private fun createPersistentGroup(aggregateRootName: String, esConnection: EsConnection) {
        logger.info("Creating the persistent group {}, it waits {} seconds until the creation.",
                aggregateRootName, timeout.duration().length(), timeout.duration().unit())

        val future = esConnection.createPersistentSubscription(aggregateRootName,
                "${aggregateRootName}SubscriptionGroup",
                PersistentSubscriptionSettingsBuilder().resolveLinkTos().roundRobin().withExtraStatistic().startFromCurrent().build(),
                null)

        try {
            Await.result(future, timeout.duration())
        } catch (ex: InvalidOperationException) {
            logger.warn("SubscriptionGroup already exists {} [{}]", aggregateRootName, ex.message)
        }
    }

}
