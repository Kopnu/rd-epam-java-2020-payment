package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Accept;
import rd.epam.java.payment.domain.entity.AcceptStatus;
import rd.epam.java.payment.repository.AcceptRepository;
import rd.epam.java.payment.service.AcceptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link AcceptService} implementation
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptServiceImpl implements AcceptService {

    private final AcceptRepository acceptRepository;

    @Override
    public Accept createAccept(Accept accept) {
        if (Objects.isNull(accept)) {
            log.error("createAccept() - error: accept is null");
            return null;
        }
        if (Objects.isNull(accept.getClientCallbackUrl())) {
            log.error("createAccept() - error: accept client callback url is null");
            return null;
        }
        if (Objects.isNull(accept.getClientPaymentUrl())) {
            log.error("createAccept() - error: accept client payment url is null");
            return null;
        }
        if (Objects.isNull(accept.getKey())) {
            log.error("createAccept() - error: accept key is null");
            return null;
        }
        log.debug("createAccept() - create accept {}", accept);
        accept.setAcceptStatus(new AcceptStatus("Приянят в обработку"));
        acceptRepository.save(accept);
        return acceptRepository.findById(accept.getAcceptId()).get();
    }

    @Override
    public Accept find(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("find() - error: id is null");
            return null;
        }
        log.debug("find() - find accept by id {}", uuid);
        return acceptRepository.findById(uuid).get();
    }

    @Override
    public List<Accept> findAll(List<UUID> ids) {
        if (Objects.isNull(ids)) {
            log.error("findAll() - error: ids is null");
            return Collections.emptyList();
        }
        log.debug("findAll() - find list of payments by id list {}", ids);
        return acceptRepository.findByIdList(ids);
    }

    @Override
    public Accept update(Accept accept) {
        if (Objects.isNull(accept)) {
            log.error("update() - error: accept is null");
            return null;
        }
        log.debug("update() - update accept {}", accept);
        return acceptRepository.update(accept).get();
    }

    @Override
    public boolean delete(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("delete() - error: id is null");
            return false;
        }
        log.debug("delete() - delete payment by id {}", uuid);
        acceptRepository.delete(uuid);
        return true;
    }
}