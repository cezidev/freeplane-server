package org.freeplane.server.persistency;

import java.util.List;

import org.freeplane.server.persistency.events.GenericEvent;
import org.freeplane.server.persistency.events.GenericEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoDbEventStore implements EventStore, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(MongoDbEventStore.class);
	
	@Autowired
	private GenericEventRepository genericEventRepository;

	@Override
	public void store(final GenericEvent genericEvent)
	{
		genericEventRepository.save(genericEvent);
	}

	@Override
	public GenericEvent findByKey(String id, long version) {
		return genericEventRepository.findByKey(id, version);
	}

	@Override
	public List<GenericEvent> findById(String id) {
		return genericEventRepository.findById(id);
	}

	@Override
	public List<GenericEvent> findByMapId(String mapId) {
		return genericEventRepository.findByMapId(mapId);
	}

	@Override
	public List<GenericEvent> findByMapIdAndNodeId(String mapId, String nodeId) {
		return genericEventRepository.findByMapIdAndNodeId(mapId, nodeId);
	}

	@Override
	public List<GenericEvent> findByMapIdAndNodeIdAndContentType(String mapId,
			String nodeId, String contentType) {
		return genericEventRepository.findByMapIdAndNodeIdAndContentType(mapId, nodeId, contentType);
	}

	@Override
	public List<GenericEvent> findByMapIdAndContentType(String mapId,
			String contentType) {
		return genericEventRepository.findByMapIdAndContentType(mapId, contentType);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("MongoDbEventStore: creating GenericEvent!");
		final String json = "{\"content\": \"PRODX2!!!\");";
		GenericEvent genericEvent = new GenericEvent("mapId1", "nodeId1", "CORE", json);
		store(genericEvent);
	}
}